package interfaces.sealed_interfaces.sealed_class;

sealed interface Moveable permits SealedGameCharacter {
    void move(int x, int y);
}
