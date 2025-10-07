package multithreading.virtual;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.*;

public class VirtualThreadFileIODemo {
    private static final int TASKS = 1000;       // number of tasks
    private static final int FILES_PER_TASK = 20; // how many files per task

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Fixed thread pool test...");
        runTest(Executors.newFixedThreadPool(100));

        System.out.println("\nVirtual thread test...");
        runTest(Executors.newVirtualThreadPerTaskExecutor());
    }

    private static void runTest(ExecutorService executor) throws InterruptedException {
        long start = System.currentTimeMillis();

        for (int i = 0; i < TASKS; i++) {
            int taskId = i;
            executor.submit(() -> performFileIO(taskId));
        }

        executor.shutdown();
        executor.awaitTermination(30, TimeUnit.SECONDS);

        long time = System.currentTimeMillis() - start;
        System.out.println("Completed " + TASKS + " tasks in " + time + " ms");
    }

    private static void performFileIO(int id) {
        for (int j = 0; j < FILES_PER_TASK; j++) {
            try {
                Path temp = Files.createTempFile("temp_" + id + "_" + j, ".txt");
                Files.writeString(temp, "Data for task " + id + ", file " + j);
                try (BufferedReader br = new BufferedReader(new FileReader(temp.toFile()))) {
                    String data = br.readLine();
                    assert (data.contains(String.valueOf(id)));

                    if (data.length() == 0) System.err.println("Unexpected empty file!");
                    Files.delete(temp);
                    Thread.sleep(50);
                } catch (IOException e) {
                    System.err.println("I/O error in task " + id + ": " + e.getMessage());
                }
            }
            catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
