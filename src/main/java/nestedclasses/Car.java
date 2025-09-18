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
            // Engine can access the private instance variable model
        }
    }
}
