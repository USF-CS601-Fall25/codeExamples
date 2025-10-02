package multithreading.basic;

/** A simple example demonstrating three threads (main, Thread1, Thread2).
 *  Shows how to create threads, how to start them, and the use of "join".
 *  Also shows that operations of Thread1 and Thread2 can be "interleaved".
 */
public class PrintTaskExample {
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new PrintTask(3), "Thread1");
		int n = 3;
		Thread t2 = new Thread(() -> {
            for (int i = 0; i < n; i++) {
                System.out.println(i + " " + Thread.currentThread().getName());

            }
        });
		//new PrintTask(3), "Thread2");
		t1.start();
		t2.start();

		// System.out.println(" Done " + Thread.currentThread().getName());
		// main thread will wait for t1 and t2 to finish before printing Done
		t1.join();
		t2.join();
		System.out.println("Done "); // Done will be printed in the end if we use join()

	}
}
