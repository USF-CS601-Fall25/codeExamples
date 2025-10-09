package multithreading.atomicyproblem;

// Take a look at this example:
// Two threads modify the same shared variable count: one increments, one decrements.
// Expected result: 0
// Actual result: unpredictable! Sometimes 0, sometimes positive, sometimes negative.
// Because count++ and count-- are not atomic.  Should be treated as atomic.
public class IncrementDecrementCounter {
	static int count = 0;

	public static void main(String[] args) throws InterruptedException {
		Runnable incrementTask = () -> {
			for (int i = 0; i < 10_000; i++) {
				count++; // not atomic
			}
		};

		Runnable decrementTask = () -> {
			for (int i = 0; i < 10_000; i++) {
				count--; // not atomic
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