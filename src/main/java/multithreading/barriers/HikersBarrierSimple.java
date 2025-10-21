package multithreading.barriers;

import java.util.concurrent.CyclicBarrier;

public class HikersBarrierSimple {
    public static void main(String[] args) {
        int numHikers = 3;
        int numCheckpoints = 3;

        CyclicBarrier barrier = new CyclicBarrier(numHikers, () -> {
            System.out.println("All hikers reached the checkpoint! Starting the next leg."); // barrier action: tprinted once all hikes arrive at a check point
        });

        // Start each hiker (thread)
        for (int i = 1; i <= numHikers; i++) {
            int id = i;
            new Thread(() -> hike(id, numCheckpoints, barrier)).start();
        }
    }

    private static void hike(int hikerId, int numCheckpoints, CyclicBarrier barrier) {
        try {
            for (int checkpoint = 1; checkpoint <= numCheckpoints; checkpoint++) {
                System.out.println("Hiker " + hikerId + " is hiking to checkpoint " + checkpoint + "...");
                Thread.sleep(1000);
                System.out.println("Hiker " + hikerId + " arrived at checkpoint " + checkpoint + ".");

                // Wait for other hikers before starting next leg
                barrier.await();// Once all hikers have called await(), the barrier releases them all at once, and they continue hiking the next leg of the hike.
            }
            System.out.println("Hiker " + hikerId + " finished the trail!");
        } catch (Exception e) {
            System.out.println("Hiker " + hikerId + " was interrupted.");
        }
    }
}

