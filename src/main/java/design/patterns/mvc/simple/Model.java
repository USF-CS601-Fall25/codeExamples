package design.patterns.mvc.simple;


public interface Model {
    void registerObserver(Observer o);
    void notifyObservers();
    void changeName(String name1, String name2);
}
