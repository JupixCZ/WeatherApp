package weatherapp.drawings;

import java.awt.image.BufferedImage;
import java.io.File;
import weatherapp.domain.weather.MeteocentrumDataContainer;
import weatherapp.domain.weather.MeteocentrumWeather;
import weatherapp.utils.FileReader;

public class MeteocentrumDrawer {

    public static BufferedImage enrichWallpaperImage(MeteocentrumDataContainer meteocentrumDataContainer, BufferedImage wallpaperImage) {
        wallpaperImage = getEnrichedWallpaperImage(meteocentrumDataContainer, wallpaperImage);

        return wallpaperImage;
    }

    private static BufferedImage getEnrichedWallpaperImage(MeteocentrumDataContainer meteocentrumDataContainer, BufferedImage wallpaperImage) {
        MeteocentrumWeather meteocentrumWeather = meteocentrumDataContainer.getWeatherNow();
        
        File weatherFile = new File(meteocentrumWeather.getWeather().getPath());
        BufferedImage weatherImage = FileReader.getImage(weatherFile);

        WallDrawer.drawOnImage(wallpaperImage, weatherImage, 200, 200);

        return wallpaperImage;
    }
}
