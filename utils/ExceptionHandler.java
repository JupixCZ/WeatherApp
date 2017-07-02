package weatherapp.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ExceptionHandler {

    public static void handleException(Exception e) {
        BufferedWriter writer = null;
        File log = new File("log.txt");

        try {
            writer = new BufferedWriter(new FileWriter(log));
            writer.write(e.toString());
        } catch (IOException exc) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
                System.exit(-1);
            } catch (IOException exc) {
            }

        }
    }
}
