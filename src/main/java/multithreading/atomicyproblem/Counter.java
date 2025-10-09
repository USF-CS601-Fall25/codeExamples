package multithreading.atomicyproblem;

// Take a look at this example: why isn't the final count always 20000?
// This is NOT a visibility problem (volatile would not help).
// It’s an atomicity problem:
// Hint: count++ looks like one step, but it actually involves three operations:
// 1) read count, 2) add 1, 3) write count back.
// If threads interleave these steps, updates get lost.
public class Counter {
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            for (int i = 0; i < 10_000; i++) {
                count++;
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Final count: " + count);
    }
}