package multithreading.volatilevariables;

import java.util.concurrent.TimeUnit;

/** Example from Effective Java book. The program now stops because we use volatile that guarantees visibility. */
public class StopThreadWithVolatile {
    private static volatile boolean stopRequested = false;

    public static void main (String[] args) {
        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            while (!stopRequested) {
                 i++;
            } // while
        }); //Thread

        backgroundThread.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        }
        catch (InterruptedException e) {
            System.out.println("Thread was interrupted.");
        }

        stopRequested = true;
        System.out.println(stopRequested);
    }
}
