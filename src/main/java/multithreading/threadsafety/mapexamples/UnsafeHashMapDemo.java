package multithreading.threadsafety.mapexamples;

import java.util.HashMap;
import java.util.Map;

public class UnsafeHashMapDemo {
    public static void main(String[] args) throws InterruptedException {
        Map<Integer, String> map = new HashMap<>();

        Runnable writer = () -> {
            for (int i = 0; i < 100000; i++) {
                map.put(i, "Value " + i);
            }
        };

        Thread t1 = new Thread(writer);
        Thread t2 = new Thread(writer);
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Final size: " + map.size());
        System.out.println("Done!");
    }
}

