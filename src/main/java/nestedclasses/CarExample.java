package nestedclasses;

public class CarExample {
    public static void main(String[] args) {
        // Create a Car object
        Car myCar = new Car("Tesla Model S", 670);
        // Create an Engine tied to *that* Car
        Car.Engine engine = myCar.new Engine();
        // Start the engine
        engine.start();

    }
}
