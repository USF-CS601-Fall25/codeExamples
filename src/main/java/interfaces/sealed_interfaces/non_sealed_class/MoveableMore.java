package interfaces.sealed_interfaces.non_sealed_class;

// Compiler error if try to extend Moveable
public interface MoveableMore { // extends Moveable {
    void draw();
}
