package weatherapp.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class FileReader {

    public static Document getContent(String path) {
        Document document = null;

        File file = new File(path);

        try {
            document = Jsoup.parse(file, null);
        } catch (IOException exception) {
            System.out.println("Error reading file " + path);
            System.out.println(exception);
        }

        return document;
    }

    public static File getFile(String path) {
        return new File(path);
    }
    
    public static File createFileFromImage(BufferedImage image, String path){
        File outputfile = new File(path);
        
        try {
            ImageIO.write(image, "png", outputfile);
        } catch (IOException ex) {
            Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return outputfile;
    }

    public static BufferedImage getImage(File file) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            System.out.println("Error reading file for image.");
            System.exit(-1);
        }
        
        return image;
    }
}
