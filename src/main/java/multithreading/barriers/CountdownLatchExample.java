package multithreading.barriers;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// CountDownLatch is one of synchronization barriers in Java
public class CountdownLatchExample {
    public static final int NUM_THREADS = 3;
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(NUM_THREADS);
        ExecutorService poolManager = Executors.newFixedThreadPool(NUM_THREADS);

        for(int x = 0; x < NUM_THREADS; x++) {
            poolManager.submit(() -> { // submitting a Runnable task
                System.out.println("Doing some important work here ...");
                latch.countDown(); // finished the work, decrement the count
            });
        }

        try {
            latch.await(); // main thread waits for the count to be 0
            System.out.println("All done!");
            poolManager.shutdownNow();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
