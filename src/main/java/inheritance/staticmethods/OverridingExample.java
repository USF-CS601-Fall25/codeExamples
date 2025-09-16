package inheritance.staticmethods;

/** The example demonstrates that static methods do hot behave polymorphically. */
public class OverridingExample {
    public static void main(String[] args) {
        Superclass sub = new Subclass();
        sub.func1(); // child's version

        // Both will call the parent's staticFunc1
        Superclass.staticFunc1();
        sub.staticFunc1(); // Calls parent's staticFunc1 because the compiler looks at the type of the variable sub

        // Which function will be invoked?
       // Subclass.staticFunc1();


    }
}
