package inheritance.staticmethods;

/** The example demonstrates that static methods do hot behave polymorphically. */
public class OverridingExample {
    public static void main(String[] args) {
        Superclass sub = new Subclass();
        sub.func1();
        sub.staticFunc1();

    }
}
