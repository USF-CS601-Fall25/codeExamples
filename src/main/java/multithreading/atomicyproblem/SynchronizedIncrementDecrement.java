package multithreading.atomicyproblem;

/**
 * Example: Using synchronized block to protect a "critical section",
 * where we modify the shared variable count.
 * The result will always be 0 in this example.
 */
public class SynchronizedIncrementDecrement {
	private int count = 0; // shared variable

	public static void main(String[] args) throws InterruptedException {
		SynchronizedIncrementDecrement example = new SynchronizedIncrementDecrement();
		example.runExample();
	}

	public void runExample() throws InterruptedException {
		Runnable incrementTask = () -> {
			for (int i = 0; i < 10_000; i++) {
				synchronized (this) {   // critical section protected by this object's lock
					count++;            // only one thread at a time
				}
			}
		};

		Runnable decrementTask = () -> {
			for (int i = 0; i < 10_000; i++) {
				synchronized (this) {   // same lock as above (this)
					count--;            // critical section - only one thread at a time; if another thread is doing count++, we can't get the lock
				}
			}
		};

		Thread t1 = new Thread(incrementTask);
		Thread t2 = new Thread(decrementTask);

		t1.start();
		t2.start();
		t1.join();
		t2.join();

		System.out.println("Final count: " + count);
	}
}
