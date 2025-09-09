package interfaces.sealed_interfaces.non_sealed_class;

sealed interface Moveable permits GameCharacter {
    void move(int x, int y);
}
