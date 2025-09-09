package interfaces.sealed_interfaces.sealed_class;

public sealed class SealedGameCharacter implements Moveable permits Monster{
    private String name;
    private int currX;
    private int currY;

    public SealedGameCharacter(String name, int currX, int currY) {
        this.name = name;
        this.currX = currX;
        this.currY = currY;
    }

   // @Override
    public void move(int x, int y) {
       currX = x;
       currY = y;
    }

}
