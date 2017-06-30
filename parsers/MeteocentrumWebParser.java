package weatherapp.parsers;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import weatherapp.domain.weather.Bio;
import weatherapp.domain.weather.MeteoDataContainer;
import weatherapp.domain.weather.MeteocentrumDataContainer;
import weatherapp.enums.ModuleType;
import weatherapp.utils.FileReader;
import weatherapp.utils.UrlReader;

public class MeteocentrumWebParser extends WebParser {

    private MeteoDataContainer meteoDataContainer;
    private final String todayBaseURL = "http://www.meteocentrum.cz/predpoved-pocasi/cz/5917/usti-nad-labem/den/1";
    private final String todayBaseTestFile = "meteocentrumTodayBaseTest.html";

    public MeteocentrumWebParser() {
        moduleType = ModuleType.METEOCENTRUM;
    }

    public void parseBaseTodayWeather() {
        parseBaseWeather(todayBaseURL);
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
        Document baseTestWeatherDoc = FileReader.getContent(todayBaseTestFile);

        Elements elements = baseTestWeatherDoc.getElementsByClass("description");
        String s = elements.text();
        Bio bio = getBio(baseTestWeatherDoc);

        return new MeteocentrumDataContainer();
    }

    @Override
    protected void refreshMeteoData() {
        parseBaseTodayWeather();

        meteoDataContainer = new MeteocentrumDataContainer();
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
