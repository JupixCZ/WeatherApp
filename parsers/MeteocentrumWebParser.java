package weatherapp.parsers;

import org.jsoup.nodes.Document;
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

        return new MeteocentrumDataContainer();
    }

    @Override
    protected void refreshMeteoData() {
        parseBaseTodayWeather();

        meteoDataContainer = new MeteocentrumDataContainer();
    }
}
