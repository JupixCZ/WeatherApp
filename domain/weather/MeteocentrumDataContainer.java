package weatherapp.domain.weather;

import java.util.List;

public class MeteocentrumDataContainer extends MeteoDataContainer {

    private final Bio bio;
    private final MeteocentrumWeather weatherNow;
    private MeteocentrumWeather weatherNext;
    private MeteocentrumWeather weatherLater;
    
    private List<MeteocentrumWeather> weatherForecast;

    public MeteocentrumDataContainer(Bio bio, List<MeteocentrumWeather> weatherToday, List<MeteocentrumWeather> weatherForecast) {
        this.bio = bio;
        
        MeteocentrumWeather weatherNowArg = weatherToday.get(0);
        this.weatherNow = weatherNowArg;
    }

    public Bio getBio() {
        return bio;
    }
    
    public MeteocentrumWeather getWeatherNow(){
        return weatherNow;
    }
}
