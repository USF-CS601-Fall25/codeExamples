package design.patterns.creatingobjects.staticfactory;

public class UFOShip implements EnemyShip {

    public UFOShip() {

    }
    @Override
    public void attack() {
        System.out.println("UFO attack");
    }
}
