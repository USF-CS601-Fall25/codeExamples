package interfaces.sealed_interfaces.sealed_class;

public non-sealed class Monster extends SealedGameCharacter {
    public Monster(String name, int currX, int currY) {
        super(name, currX, currY);
    }

    @Override
    public void move(int x, int y) {
        System.out.println("Monster move");
    }


}
