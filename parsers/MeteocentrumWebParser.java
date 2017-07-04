package weatherapp.parsers;

import java.util.ArrayList;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import weatherapp.domain.weather.Bio;
import weatherapp.domain.weather.MeteoDataContainer;
import weatherapp.domain.weather.MeteocentrumDataContainer;
import weatherapp.domain.weather.MeteocentrumWeather;
import weatherapp.enums.ModuleType;
import weatherapp.enums.Resource;
import weatherapp.utils.FileReader;
import weatherapp.utils.UrlReader;

public class MeteocentrumWebParser extends WebParser {

    private MeteoDataContainer meteoDataContainer;
    private final int TIME_SCOPE = 6;

    public MeteocentrumWebParser() {
        moduleType = ModuleType.METEOCENTRUM;
    }

    public void parseBaseTodayWeather() {
        Document todayBaseWeatherDoc = UrlReader.getContent(Resource.METEO_TODAY_BASE_URL.getPath());
        Document tomorrowBaseWeatherDoc = UrlReader.getContent(Resource.METEO_TOMORROW_BASE_URL.getPath());

        String description = todayBaseWeatherDoc.getElementsByClass("description").text();
        Bio bio = getBio(todayBaseWeatherDoc);

        List<MeteocentrumWeather> weatherToday = new ArrayList<>();
        MeteocentrumWeather weatherNow = getWeatherNow(todayBaseWeatherDoc);

        int possibleTimeScope = weatherNow.getHour() + TIME_SCOPE;
        int maxTimeScope = possibleTimeScope > 24 ? possibleTimeScope - 24 : possibleTimeScope;

        MeteocentrumWeather weatherNext = getWeatherNext(todayBaseWeatherDoc, tomorrowBaseWeatherDoc, weatherNow, maxTimeScope);
        MeteocentrumWeather weatherLater = getWeatherNext(todayBaseWeatherDoc, tomorrowBaseWeatherDoc, weatherNext, maxTimeScope);

        weatherToday.add(weatherNow);
        weatherToday.add(weatherNext);
        weatherToday.add(weatherLater);

        meteoDataContainer = new MeteocentrumDataContainer(bio, weatherToday, null);
    }

    @Override
    protected void healthCheck() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ModuleType getModuleType() {
        return moduleType;
    }

    @Override
    public MeteoDataContainer getMeteoData() {
        refreshMeteoData();

        return meteoDataContainer;
    }

    @Override
    public MeteoDataContainer getTestMeteoData() {
        Document baseTestWeatherDoc = FileReader.getContent(Resource.METEO_TODAY_BASE_TEST_FILE.getPath());

        String description = baseTestWeatherDoc.getElementsByClass("description").text();
        Bio bio = getBio(baseTestWeatherDoc);

        List<MeteocentrumWeather> weatherToday = new ArrayList<>();
        MeteocentrumWeather weatherNow = getWeatherNow(baseTestWeatherDoc);

        weatherToday.add(weatherNow);

        return new MeteocentrumDataContainer(bio, weatherToday, null);
    }

    @Override
    protected void refreshMeteoData() {
        parseBaseTodayWeather();
    }

    //////////////////PARSING METHODS/////////////////////
    private Bio getBio(Document document) {
        final int length = 5;

        String text = document.getElementsByClass("description").text();
        int index = text.indexOf("Bio") + length;

        String bioString = text.substring(index, index + 1);
        int bio = Integer.parseInt(bioString);

        return new Bio(bio);
    }

    private MeteocentrumWeather getWeatherNow(Document weatherTodayDoc) {
        Elements elements = weatherTodayDoc.getElementsByClass("forecast-table-vertical").select("tr");
        List<Element> elementList = elements.subList(2, elements.size());
        Resource.Weather weather;

        int currentHour = 24;
        boolean readyToBrake = false;
        Integer state = null;

        for (Element element : elementList) {
            Elements weatherData = element.select("td");
            String weatherDataString = weatherData.html();
            String[] dataLines = weatherDataString.split("\\n", 2);

            if (readyToBrake) {
                String timeLine = dataLines[0];
                currentHour = Integer.parseInt(timeLine.substring(0, timeLine.length() - 3));
                currentHour--;
                break;
            } else {
                if (state == null) {    //countering case if time is 24.00
                    state = getWeatherState(dataLines);
                }
                if (dataLines[0].contains("Aktuálně")) {
                    state = getWeatherState(dataLines);

                    readyToBrake = true;
                }
            }
        }

        weather = Resource.Weather.getResourceByState(state);

        return new MeteocentrumWeather(currentHour, weather);
    }

    private MeteocentrumWeather getWeatherNext(Document weatherTodayDoc, Document weatherTomorrowDoc, MeteocentrumWeather previousWeather, int maxTimeScope) {
        if (previousWeather == null) {
            return null;
        }

        int previousWeatherTime = previousWeather.getHour();
        int previousWeatherState = previousWeather.getWeather().getState();

        Elements elements = weatherTodayDoc.getElementsByClass("forecast-table-vertical").select("tr");
        List<Element> elementList = elements.subList(2, elements.size());

        int currentHour;
        int currentWeatherState;
        boolean neededTomorrowCheck = previousWeatherTime > maxTimeScope;

        for (Element element : elementList) {
            Elements weatherData = element.select("td");
            String weatherDataString = weatherData.html();
            String[] dataLines = weatherDataString.split("\\n", 2);

            String timeLine = dataLines[0];
            if (timeLine.contains("Aktuálně")) {
                continue;
            }

            currentHour = Integer.parseInt(timeLine.substring(0, timeLine.length() - 3));
            if (currentHour < previousWeatherTime) {
                continue;
            } else if (!neededTomorrowCheck && currentHour > maxTimeScope) {
                break;
            }

            currentWeatherState = getWeatherState(dataLines);
            if (currentWeatherState != previousWeatherState) {
                Resource.Weather nextWeatherState = Resource.Weather.getResourceByState(currentWeatherState);
                return new MeteocentrumWeather(currentHour, nextWeatherState);
            }
        }

        if (neededTomorrowCheck) {
            elements = weatherTomorrowDoc.getElementsByClass("forecast-table-vertical").select("tr");
            elementList = elements.subList(2, elements.size());

            for (Element element : elementList) {
                Elements weatherData = element.select("td");
                String weatherDataString = weatherData.html();
                String[] dataLines = weatherDataString.split("\\n", 2);

                String timeLine = dataLines[0];

                currentHour = Integer.parseInt(timeLine.substring(0, timeLine.length() - 3));
                if (currentHour > maxTimeScope) {
                    break;
                }

                currentWeatherState = getWeatherState(dataLines);
                if (currentWeatherState != previousWeatherState) {
                    Resource.Weather nextWeatherState = Resource.Weather.getResourceByState(currentWeatherState);
                    return new MeteocentrumWeather(currentHour, nextWeatherState);
                }
            }
        }

        return null;
    }

    private int getWeatherState(String[] dataLines) {
        String wiStateLine = dataLines[1];

        return Integer.parseInt(wiStateLine.substring(wiStateLine.indexOf("state-") + 6, wiStateLine.indexOf("title") - 2));
    }
}
