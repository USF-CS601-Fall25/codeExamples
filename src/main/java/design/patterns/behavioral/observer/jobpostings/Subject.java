package design.patterns.behavioral.observer.jobpostings;

public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
    String getInfo();
}
