package weatherapp.drawings;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class WallDrawer {

    public static File createImageCopy(File file, String path) {
        BufferedImage image = null;
        
        try {

            image = ImageIO.read(file);

            ImageIO.write(image, "png", new File(path));

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return new File(path);
    }
}
