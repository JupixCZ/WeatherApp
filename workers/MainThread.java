package weatherapp.workers;

public class MainThread implements Runnable {

    private Thread mainThread;
    private final int refreshRate;
    private final int MILIS_IN_MINUTE = 60000;

    public MainThread(short refreshRateInMins) {
        this.refreshRate = refreshRateInMins * MILIS_IN_MINUTE;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("refresh");
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
