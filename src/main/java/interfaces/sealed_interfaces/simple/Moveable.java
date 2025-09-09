package interfaces.sealed_interfaces.simple;

sealed interface Moveable permits GameCharacter {
    void move(int x, int y);
}
