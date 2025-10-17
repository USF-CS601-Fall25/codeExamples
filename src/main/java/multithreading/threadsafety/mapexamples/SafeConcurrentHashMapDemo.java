package multithreading.threadsafety.mapexamples;
import java.util.concurrent.ConcurrentHashMap;

public class SafeConcurrentHashMapDemo {
    public static void main(String[] args) throws InterruptedException {
        var map = new ConcurrentHashMap<Integer, String>();

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
