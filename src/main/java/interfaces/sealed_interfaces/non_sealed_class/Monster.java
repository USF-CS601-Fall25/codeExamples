package interfaces.sealed_interfaces.non_sealed_class;


public class Monster extends GameCharacter {
    public Monster(String name, int currX, int currY) {
        super(name, currX, currY);
    }

    @Override
    public void move(int x, int y) {
        super.move(x, y);
        System.out.println("here");
    }


}
