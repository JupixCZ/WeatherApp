package weatherapp.drawings;

import java.io.File;
import weatherapp.domain.weather.MeteocentrumDataContainer;

public class MeteocentrumDrawer {

    final static String TEST_ONLY_PATH = "resources/test2.png";
    final static String OUTPUT_PATH = "resources/out.png";

    public static File processData(MeteocentrumDataContainer meteocentrumDataContainer) {

      File file = new File(TEST_ONLY_PATH);
      return WallDrawer.createImageCopy(file, OUTPUT_PATH);
    }
}
