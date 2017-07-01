package weatherapp.enums;

public enum Resource {

    INPUT_BASE_WALLPAPER("resources/baseWalls/test.png"),
    OUTPUT_WALLPAPER("resources/temp/out-temp.png"),
    METEO_TODAY_BASE_URL("http://www.meteocentrum.cz/predpoved-pocasi/cz/5917/usti-nad-labem/den/1"),
    METEO_TODAY_BASE_TEST_FILE("resources/meteocentrumTodayBaseTest.html");

    private final String path;

    private Resource(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public enum Weather {
        
        UNKNOWN("resources/weatherIcons/unknown.png", 1),
        
        SUNNY("resources/weatherIcons/sunny.png", 2),
        MOSTLY_SUNNY("resources/weatherIcons/mostly_sunny.png", 3),
        PARTLY_CLOUDY("resources/weatherIcons/partly_cloudy.png", 4),
        CLOUDY("resources/weatherIcons/cloudy.png", 5),
        ALMOST_OVERCAST("resources/weatherIcons/almost_overcast.png", 6),
        OVERCAST("resources/weatherIcons/overcast.png", 7),
        LOW_CLOUDS("resources/weatherIcons/low_clouds.png", 8),
        FOGGY("resources/weatherIcons/foggy.png", 9),
        RAIN("resources/weatherIcons/rain.png", 10),
        HEAVY_RAIN("resources/weatherIcons/heavy_rain.png", 11),
        LIGHT_RAIN("resources/weatherIcons/light_rain.png", 12),
        RAIN_SHOWERS("resources/weatherIcons/rain_showers.png", 13),
        T_STORMS("resources/weatherIcons/t_storms.png", 14),
        LOCAL_STORMS("resources/weatherIcons/local_storms.png", 15),
        SNOW("resources/weatherIcons/snow.png", 16),
        HEAVY_SNOW("resources/weatherIcons/heavy_snow.png", 17),
        LIGHT_SNOW("resources/weatherIcons/light_snow.png", 18),
        SNOW_SHOWERS("resources/weatherIcons/snow_showers.png", 19),
        RAIN_WITH_SNOW("resources/weatherIcons/rain_with_snow.png", 20),
        LIGHT_RAIN_WITH_SNOW("resources/weatherIcons/light_rain_with_snow.png", 21),
        MIXED_SHOWERS("resources/weatherIcons/mixed_showers.png", 22),
        FREEZING_RAIN("resources/weatherIcons/freezing_rain.png", 23),
        FREEZING_RAIN_SHOWERS("resources/weatherIcons/freezing_rain_showers.png", 24),
        HAIL("resources/weatherIcons/hail.png", 25),
        
        NIGHT_SUNNY("resources/weatherIcons/night_sunny.png", 26),
        NIGHT_MOSTLY_SUNNY("resources/weatherIcons/night_mostly_sunny.png", 27),
        NIGHT_PARTLY_CLOUDY("resources/weatherIcons/night_partly_cloudy.png", 28),
        NIGHT_CLOUDY("resources/weatherIcons/night_cloudy.png", 29),
        NIGHT_ALMOST_OVERCAST("resources/weatherIcons/night_almost_overcast.png", 30),
        NIGHT_LOW_CLOUDS("resources/weatherIcons/night_low_clouds.png", 31),
        NIGHT_RAIN_SHOWERS("resources/weatherIcons/night_rain_showers.png", 32),
        NIGHT_LOCAL_STORMS("resources/weatherIcons/night_local_storms.png", 33),
        NIGHT_SNOW_SHOWERS("resources/weatherIcons/night_snow_showers.png", 34),
        NIGHT_MIXED_SHOWERS("resources/weatherIcons/night_mixed_showers.png", 35),
        NIGHT_FREEZING_RAIN_SHOWERS("resources/weatherIcons/night_freezing_rain_showers.png", 36);


        private final String path;
        private final int state;

        private Weather(String path, int state) {
            this.path = path;
            this.state = state;
        }

        public String getPath() {
            return path;
        }
    }
}
