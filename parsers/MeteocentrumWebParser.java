package weatherapp.parsers;

import java.util.ArrayList;
import java.util.List;
import org.jsoup.nodes.Document;
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

        if (baseWeatherDoc != null) {
            // System.out.println(baseWeatherDoc);
        }
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

        Elements elements = baseTestWeatherDoc.getElementsByClass("description");
        String s = elements.text();
        Bio bio = getBio(baseTestWeatherDoc);
        
        List<MeteocentrumWeather> weatherToday = new ArrayList<>();
        MeteocentrumWeather weatherNow = new MeteocentrumWeather(0, Resource.Weather.RAIN);
        
        weatherToday.add(weatherNow);

        return new MeteocentrumDataContainer(bio, weatherToday, null);
    }

    @Override
    protected void refreshMeteoData() {
        parseBaseTodayWeather();

        //meteoDataContainer = new MeteocentrumDataContainer();
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
}
