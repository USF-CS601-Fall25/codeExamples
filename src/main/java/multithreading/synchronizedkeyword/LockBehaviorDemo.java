package multithreading.synchronizedkeyword;

/**
 * Demonstrates how using the same or different lock objects
 * affects whether threads can run synchronized code at the same time.
 */
public class LockBehaviorDemo {
    private final Object lock; // shared or private lock, depending on what's passed to the constructor

    public LockBehaviorDemo(Object lock) {
        this.lock = lock;
    }

    private void doWork(String workerName) {
        synchronized (lock) {
            System.out.println(workerName + " acquired lock.");
            try {
                Thread.sleep(1000); // pretend to do some work, while holding the lock
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println(workerName + " released lock.");
        }
    }

    public static void main(String[] args) {
        // Case1 1: Two threads share the SAME lock → must take turns
        Object sharedLock = new Object();
        LockBehaviorDemo demo1 = new LockBehaviorDemo(sharedLock);
        Thread t1 = new Thread(() -> demo1.doWork("Thread A"));
        Thread t2 = new Thread(() -> demo1.doWork("Thread B"));

        // Case 2: Two threads have DIFFERENT locks → run in parallel
        LockBehaviorDemo demo2a = new LockBehaviorDemo(new Object());
        LockBehaviorDemo demo2b = new LockBehaviorDemo(new Object());
        Thread t3 = new Thread(() -> demo2a.doWork("Thread A"));
        Thread t4 = new Thread(() -> demo2b.doWork("Thread B"));

        System.out.println("=== Shared lock: sequential execution ===");
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\n=== Separate locks: parallel execution ===");
        t3.start();
        t4.start();
    }
}

