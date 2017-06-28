package weatherapp.parsers;

public class MeteocentrumWebParser extends WebParser{

    private final String todayBaseURL = "http://www.meteocentrum.cz/predpoved-pocasi/cz/5917/usti-nad-labem/den/1";

    public MeteocentrumWebParser() {

    }

    public void parseBaseTodayWeather() {
        parseBaseWeather(todayBaseURL);
    }

    private void parseBaseWeather(String URL) {
        
    }

    @Override
    protected void healthCheck() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
