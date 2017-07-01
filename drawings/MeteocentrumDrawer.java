package weatherapp.drawings;

import java.awt.image.BufferedImage;
import java.io.File;
import weatherapp.domain.weather.MeteocentrumDataContainer;
import weatherapp.utils.FileReader;

public class MeteocentrumDrawer {

    final static String OUTPUT_PATH = "resources/out.png";
    final static String MOON_PATH = "resources/weatherIcons/moon.png";

    public static BufferedImage enrichWallpaperImage(MeteocentrumDataContainer meteocentrumDataContainer, BufferedImage wallpaperImage) {
        wallpaperImage = getEnrichedWallpaperImage(wallpaperImage);

        return wallpaperImage;
    }

    private static BufferedImage getEnrichedWallpaperImage(BufferedImage wallpaperImage) {
        File moon = new File(MOON_PATH);
        BufferedImage moonImage = FileReader.getImage(moon);

        WallDrawer.drawOnImage(wallpaperImage, moonImage, 200, 200);

        return wallpaperImage;
    }
}
