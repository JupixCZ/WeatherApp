package weatherapp.drawings;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import weatherapp.domain.weather.MeteocentrumDataContainer;

public class MeteocentrumDrawer {

    final static String TEST_ONLY_PATH = "resources/test2.png";
    final static String OUTPUT_PATH = "resources/out.png";
    final static String OUTPUT_TEMP_PATH = "resources/out-temp.png";
    final static String MOON_PATH = "resources/moon.png";

    public static File processData(MeteocentrumDataContainer meteocentrumDataContainer) {

        
        File file = new File(TEST_ONLY_PATH);
        draw(file);
        
        return new File(OUTPUT_TEMP_PATH);
        
        //return WallDrawer.createImageCopy(file, OUTPUT_PATH);
    }

    private static void draw(File baseWall) {
        BufferedImage baseWallImage = null;

        try {
            baseWallImage = ImageIO.read(baseWall);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        File moon = new File(MOON_PATH);

        BufferedImage moonImage = null;

        try {
            moonImage = ImageIO.read(moon);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

//        BufferedImage bimage = new BufferedImage(200, 200,
//              BufferedImage.TYPE_BYTE_INDEXED);
        Graphics2D g2d = baseWallImage.createGraphics();

        g2d.setColor(Color.red);
        g2d.drawImage(moonImage, null, 100, 100);
        //g2d.fill(new Ellipse2D.Float(0, 0, 200, 100));
        g2d.dispose();
        
        File outputfile = new File(OUTPUT_TEMP_PATH);

        try {
            ImageIO.write(baseWallImage, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
