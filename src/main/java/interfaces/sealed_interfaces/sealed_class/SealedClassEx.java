package interfaces.sealed_interfaces.sealed_class;


public class SealedClassEx {
    public static void main(String[] args) {
        // Will work, we explicitly allowed Monster
       Monster m = new Monster("A", 0, 0);
        m.move(5, 6);
    }
}
