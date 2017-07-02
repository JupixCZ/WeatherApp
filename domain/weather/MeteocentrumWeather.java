package weatherapp.domain.weather;

import weatherapp.enums.Resource;

public class MeteocentrumWeather {

    private final Integer hour;
    private final Integer maxDayTemperature;
    private final Integer minNightTemperature;
    private final Resource.Weather weather;

    public MeteocentrumWeather(Integer hour, Resource.Weather weather) {
        this.hour = hour;
        this.maxDayTemperature = null;
        this.minNightTemperature = null;
        this.weather = weather;
    }

    public MeteocentrumWeather(Integer hour, int maxDayTemperature, int minNightTemperature, Resource.Weather weather) {
        this.hour = hour;
        this.maxDayTemperature = maxDayTemperature;
        this.minNightTemperature = minNightTemperature;
        this.weather = weather;
    }

    public int getHour() {
        return hour;
    }

    public Resource.Weather getWeather() {
        return weather;
    }

    public int getMaxDayTemperature() {
        return maxDayTemperature;
    }

    public int getMinNightTemperature() {
        return minNightTemperature;
    }
}
