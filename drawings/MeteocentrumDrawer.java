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
        enrichWithBaseWeatherToday(meteocentrumDataContainer, wallpaperImage);

        return wallpaperImage;
    }

    private static void enrichWithBaseWeatherToday(MeteocentrumDataContainer meteocentrumDataContainer, BufferedImage wallpaperImage) {
        int baseXPosition = 700;
        int baseYPosition = 400;
        int xIncrement = 400;

        MeteocentrumWeather meteocentrumWeatherNow = meteocentrumDataContainer.getWeatherNow();
        File nowWeatherFile = new File(meteocentrumWeatherNow.getWeather().getPath());
        BufferedImage nowWeatherImage = FileReader.getImage(nowWeatherFile);

        MeteocentrumWeather meteocentrumWeatherNext = meteocentrumDataContainer.getWeatherNext();
        BufferedImage nextWeatherImage = null;
        if (meteocentrumWeatherNext != null) {
            File nextWeatherFile = new File(meteocentrumWeatherNext.getWeather().getPath());
            nextWeatherImage = FileReader.getImage(nextWeatherFile);

            baseXPosition = baseXPosition - 200;
        }

        MeteocentrumWeather meteocentrumWeatherLater = meteocentrumDataContainer.getWeatherLater();
        BufferedImage laterWeatherImage = null;
        if (meteocentrumWeatherLater != null) {
            File laterWeatherFile = new File(meteocentrumWeatherLater.getWeather().getPath());
            laterWeatherImage = FileReader.getImage(laterWeatherFile);

            baseXPosition = baseXPosition - 200;
        }

        WallDrawer.drawOnImage(wallpaperImage, nowWeatherImage, baseXPosition, baseYPosition);

        if (nextWeatherImage != null) {
            WallDrawer.drawOnImage(wallpaperImage, nextWeatherImage, baseXPosition + xIncrement, baseYPosition);
        }

        if (laterWeatherImage != null) {
            WallDrawer.drawOnImage(wallpaperImage, laterWeatherImage, baseXPosition + (xIncrement * 2), baseYPosition);
        }
    }
}
