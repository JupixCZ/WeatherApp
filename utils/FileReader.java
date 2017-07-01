package weatherapp.utils;

import java.io.File;
import java.io.IOException;
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
}
