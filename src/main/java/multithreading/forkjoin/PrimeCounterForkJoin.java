package multithreading.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class PrimeCounterForkJoin {
    public static final int THRES = 1000;

    static class PrimeCounterTask extends RecursiveTask<Integer> {
        private final int start;
        private final int end;

        public PrimeCounterTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            // if base case, compute directly
              int count = 0;
            if (end - start < THRES) {
                // count primes
                for (int i = start; i <= end; i++) {
                    if (isPrime(i))
                        count++;
                }
                return count;
            }
            // else create two subtasks of type PrimeCounterTask (for left and right half of the range)
            else {
                int mid = (start + end) / 2;
                PrimeCounterTask leftTask = new PrimeCounterTask(start, mid);
                PrimeCounterTask rightTask = new PrimeCounterTask(mid + 1, end);
                leftTask.fork();
                int rightCount = rightTask.compute();
                int leftCount = leftTask.join(); // run left task asynchronously
                return rightCount + leftCount;
            }
        }

        private boolean isPrime(int n) {
            if (n < 2) return false;
            if (n % 2 == 0 && n != 2) return false;
            for (int i = 3; i * i <= n; i += 2) {
                if (n % i == 0) return false;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();

        PrimeCounterTask task = new PrimeCounterTask(1, 100_000);
        int totalPrimes = pool.invoke(task); // main waits for the task to finish

        System.out.println("Total primes in range = " + totalPrimes);
        pool.shutdown();
    }
}