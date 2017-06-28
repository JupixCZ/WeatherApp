package weatherapp.utils;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class UrlReader {

    public static Document getContent(String URL) {
        Document document = null;

        try {
            document = Jsoup.connect(URL).get();
        } catch (IOException exception) {
            System.out.println("Error connecting to " + URL);
            System.out.println(exception);
        }

        return document;
    }

    public static void isInternetOnline() {

    }
}
