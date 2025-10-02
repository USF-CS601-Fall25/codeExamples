package multithreading.basic;

public class SolvingForMax {
        public final static int NUM_ROWS = 10;

        /** Worker task that computes the max for a row */
        private static class RowMaxTask implements Runnable {
            final double[] row;
            double max = Double.MIN_VALUE;

            public RowMaxTask(double[] row) {
                this.row = row;
            }

            @Override
            public void run() {
                for (double v : row) {
                    max = Math.max(max, v);
                }
            }

            public double getMax() {
                return max;
            }
        }

    /** Utility to create random matrix */
    public static double[][] generateBigMatrix(int n, int m) {
        double[][] matrix = new double[n][m];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                matrix[r][c] = Math.random();
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
            double[][] bigMatrix = generateBigMatrix(NUM_ROWS, 1000000);

            RowMaxTask[] rowTasks = new RowMaxTask[NUM_ROWS];  // array of workers
            Thread[] threads = new Thread[NUM_ROWS];          // array of threads

            // Create runnable tasks and start threads
            for (int i = 0; i < NUM_ROWS; i++) {
                rowTasks[i] = new RowMaxTask(bigMatrix[i]);
                threads[i] = new Thread(rowTasks[i]);
                threads[i].start();
            }

            // Join and collect results
            double max = Double.MIN_VALUE;
            try {
                for (int i = 0; i < NUM_ROWS; i++) {
                    threads[i].join();
                    max = Math.max(max, rowTasks[i].getMax());
                }
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted.");
            }

            System.out.println("Maximum value was " + max);
    }
}

