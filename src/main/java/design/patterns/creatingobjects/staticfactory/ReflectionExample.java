package design.patterns.creatingobjects.staticfactory;

import java.lang.reflect.Field;

public class ReflectionExample {
    // The example demonstrates what can be done using reflection
    public static void main(String[] args) {
        try {
            PirateShip p = new PirateShip("Sparrow");
            Field privateField = PirateShip.class.getDeclaredField("name");
            privateField.setAccessible(true);
            String name = (String)privateField.get(p);
            System.out.println(name); // was able to access a private variable
        }
        catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.println(e);
        }
    }
}
