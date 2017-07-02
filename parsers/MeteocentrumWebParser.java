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

    public MeteocentrumWebParser() {
        moduleType = ModuleType.METEOCENTRUM;
    }

    public void parseBaseTodayWeather() {
        parseBaseWeather(Resource.METEO_TODAY_BASE_URL.getPath());
    }

    private void parseBaseWeather(String URL) {
        Document baseWeatherDoc = UrlReader.getContent(URL);

        String description = baseWeatherDoc.getElementsByClass("description").text();
        Bio bio = getBio(baseWeatherDoc);

        List<MeteocentrumWeather> weatherToday = new ArrayList<>();
        MeteocentrumWeather weatherNow = getWeatherNow(baseWeatherDoc);

        weatherToday.add(weatherNow);

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

    private MeteocentrumWeather getWeatherNow(Document document) {
        Elements elements = document.getElementsByClass("forecast-table-vertical").select("tr");
        List<Element> elementList = elements.subList(2, elements.size());
        Resource.Weather weather = Resource.Weather.UNKNOWN;

        for (Element element : elementList) {
            Elements weatherData = element.select("td");
            String weatherDataString = weatherData.html();
            String[] dataLines = weatherDataString.split("\\n", 2);

            if (dataLines[0].contains("Aktuálně")) {
                String wiStateLine = dataLines[1];
                
                int state = Integer.parseInt(wiStateLine.substring(wiStateLine.indexOf("state-") + 6, wiStateLine.indexOf("title") - 2));
                weather = Resource.Weather.getResourceByState(state);
            }
        }

        return new MeteocentrumWeather(null , weather);
    }
}
