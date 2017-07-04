package weatherapp.domain.weather;

import java.util.List;

public class MeteocentrumDataContainer extends MeteoDataContainer {

    private final Bio bio;
    private final MeteocentrumWeather weatherNow;
    private final MeteocentrumWeather weatherNext;
    private final MeteocentrumWeather weatherLater;

    private List<MeteocentrumWeather> weatherForecast;

    public MeteocentrumDataContainer(Bio bio, List<MeteocentrumWeather> weatherToday, List<MeteocentrumWeather> weatherForecast) {
        this.bio = bio;

        MeteocentrumWeather weatherNowArg = weatherToday.get(0);
        this.weatherNow = weatherNowArg;

        if (weatherToday.size() > 1) {
            MeteocentrumWeather weatherNextArg = weatherToday.get(1);
            this.weatherNext = weatherNextArg;
        } else {
            this.weatherNext = null;
        }

        if (weatherToday.size() > 2) {
            MeteocentrumWeather weatherLaterArg = weatherToday.get(2);
            this.weatherLater = weatherLaterArg;
        } else {
            this.weatherLater = null;
        }
    }

    public Bio getBio() {
        return bio;
    }

    public MeteocentrumWeather getWeatherNow() {
        return weatherNow;
    }
    
    public MeteocentrumWeather getWeatherNext(){
        return weatherNext;
    }
    
    public MeteocentrumWeather getWeatherLater(){
        return weatherLater;
    }
}
