package nestedclasses;

public class Car {
    private String model;
    private int horsepower;

    public Car(String model, int horsepower) {
        this.model = model;
        this.horsepower = horsepower;
    }

    /* Inner class: Engine
     * Engine cannot exist meaningfully without being tied to a Car */
    class Engine {
        public void start() {
            System.out.println("The engine of " + model + " is starting...");
        }
    }

    public static void main(String[] args) {
        // Create a Car object
        Car myCar = new Car("Tesla Model S", 670);

        // Create an Engine tied to *that* Car
        Car.Engine engine = myCar.new Engine();

        // Start the engine
        engine.start();
    }
}
