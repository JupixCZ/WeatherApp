package weatherapp.drawings;

import java.awt.image.BufferedImage;
import java.io.File;
import weatherapp.domain.weather.MeteocentrumDataContainer;
import weatherapp.enums.Resource;
import weatherapp.utils.FileReader;

public class MeteocentrumDrawer {

    public static BufferedImage enrichWallpaperImage(MeteocentrumDataContainer meteocentrumDataContainer, BufferedImage wallpaperImage) {
        wallpaperImage = getEnrichedWallpaperImage(wallpaperImage);

        return wallpaperImage;
    }

    private static BufferedImage getEnrichedWallpaperImage(BufferedImage wallpaperImage) {
        File moon = new File(Resource.Weather.HAIL.getPath());
        BufferedImage moonImage = FileReader.getImage(moon);

        WallDrawer.drawOnImage(wallpaperImage, moonImage, 200, 200);

        return wallpaperImage;
    }
}
