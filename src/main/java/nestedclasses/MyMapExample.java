package nestedclasses;

public class MyMapExample {
    public static void main(String[] args) {
        MyMap m = new MyMap(5);
        m.put("hello", 2);
        System.out.println(m.get("hello"));

        // Can create a MyEntry separately:
        MyMap.MyEntry entry = new MyMap.MyEntry("hello", 6);
        System.out.println(entry.getKey());

    }
}
