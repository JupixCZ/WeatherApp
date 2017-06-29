package weatherapp.workers;

import weatherapp.domain.weather.MeteocentrumDataContainer;
import weatherapp.enums.ModuleType;

public class MainThread implements Runnable {

    private Thread mainThread;
    private final int refreshRate;
    private final int MILIS_IN_MINUTE = 60000;

    public MainThread(short refreshRateInMins) {
        this.refreshRate = refreshRateInMins * MILIS_IN_MINUTE;
    }

    @Override
    public void run() {
        DataProvider dataProvider = new DataProvider();
        
        try {
            while (true) {
                System.out.println("refresh");
                
                //dataProvider.prepareModulesData(ModuleType.getAllModules());
                dataProvider.prepareTestModuleData(ModuleType.METEOCENTRUM);
                MeteocentrumDataContainer meteocentrumDataContainer = dataProvider.getMeteocentrumData();
                
                System.out.println("ok");
                
                Thread.sleep(refreshRate);
            }
        } catch (InterruptedException e) {
            System.out.println("Main Thread interrupted.");
        }
        System.out.println("Main Thread exiting.");
    }

    public void start() {
        if (mainThread == null) {
            mainThread = new Thread(this);
            mainThread.start();
        }
    }

}
