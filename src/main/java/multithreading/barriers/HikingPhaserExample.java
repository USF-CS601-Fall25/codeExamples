package multithreading.barriers;

import java.util.concurrent.Phaser;

public class HikingPhaserExample {

    static class Hiker implements Runnable {
        private final Phaser phaser;
        private final String name;
        private final boolean stopAtBaseCamp;

        public Hiker(Phaser phaser, String name, boolean stopAtBaseCamp) {
            this.phaser = phaser;
            this.name = name;
            this.stopAtBaseCamp = stopAtBaseCamp;
            phaser.register(); // Register this hiker as a participant
        }

        @Override
        public void run() {
            try {
                // Phase 0:  Hike to base camp
                System.out.println(name + " hiking to base camp...");
                Thread.sleep((long)(Math.random() * 1000));
                System.out.println(name + " reached base camp!");
                phaser.arriveAndAwaitAdvance(); // wait for others at base camp

                // Some hikers end their journey here
                if (stopAtBaseCamp) {
                    System.out.println(name + " decides to stay at base camp and rest. Deregistering...");
                    phaser.arriveAndDeregister();
                    return;
                }

                // Phase 1: Hike to summit
                System.out.println(name + " hiking to the summit...");
                Thread.sleep((long)(Math.random() * 1000));
                System.out.println(name + " reached the summit!");
                phaser.arriveAndAwaitAdvance(); // wait for other summit hikers
                // Advance to yet another phase just to deregister
                phaser.arriveAndDeregister();
                System.out.println(name + " finished all phases and is leaving the group.");

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Phaser phaser = new Phaser(1); // main thread is registered
        // Others register "manually" by calling register() in the Hiker constructor

        // Three hikers — one stops early
        new Thread(new Hiker(phaser, "Alice", false)).start();
        new Thread(new Hiker(phaser, "Bob", true)).start();
        new Thread(new Hiker(phaser, "Charlie", false)).start();

        // Main thread observes and prints the phase
        for (int phase = 0; phase < 2; phase++) {
            int currentPhase = phaser.getPhase();
            phaser.arriveAndAwaitAdvance(); // wait for this phase to finish
            System.out.println("Phase " + currentPhase + " completed");
        }

        phaser.arriveAndDeregister();
        System.out.println("All phases complete!");
    }
}

