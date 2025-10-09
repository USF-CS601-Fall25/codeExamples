package multithreading.atomicyproblem;

import java.util.concurrent.atomic.AtomicInteger;

// Count is guaranteed to be a 0.
public class AtomicIncrementDecrementCounter {
	private static AtomicInteger count = new AtomicInteger(0); // atomic shared variable

	public static void main(String[] args) throws InterruptedException {
		Runnable incrementTask = () -> {
			for (int i = 0; i < 10_000; i++) {
				count.incrementAndGet(); // atomic increment (uses CAS)
			}
		};

		Runnable decrementTask = () -> {
			for (int i = 0; i < 10_000; i++) {
				count.decrementAndGet(); // atomic decrement
			}
		};

		Thread t1 = new Thread(incrementTask);
		Thread t2 = new Thread(decrementTask);

		t1.start();
		t2.start();
		t1.join();
		t2.join();

		System.out.println("Final count: " + count.get());
	}

}