package interfaces.sealed_interfaces.simple;

public class SealedEx {
    public static void main(String[] args) {
        Moveable moveable = new GameCharacter("Jack", 100, 200);
        moveable.move(20, 20);

    }
}
