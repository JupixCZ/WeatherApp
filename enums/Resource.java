package weatherapp.enums;

public enum Resource {

    INPUT_BASE_WALLPAPER("resources/baseWalls/test.png"),
    OUTPUT_WALLPAPER("resources/temp/out-temp.png"),

    METEO_TODAY_BASE_URL("http://www.meteocentrum.cz/predpoved-pocasi/cz/5917/usti-nad-labem/den/1"),
    METEO_TODAY_BASE_TEST_FILE("resources/meteocentrumTodayBaseTest.html"),

    MOON("resources/weatherIcons/moon.png");

    private final String path;

    private Resource(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
