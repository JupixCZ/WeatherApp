package weatherapp;

import weatherapp.workers.MainThread;

public class WeatherApp {

    public static void main(String[] args) {
        short refreshTimeInMins = 1;
        MainThread mainThread = new MainThread(refreshTimeInMins);

        mainThread.start();
    }

}
