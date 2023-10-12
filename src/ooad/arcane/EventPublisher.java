package ooad.arcane;

import java.util.ArrayList;
import java.util.List;

public class EventPublisher {
    private final List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String eventMessage) {
        for (Observer observer : observers) {
            observer.update(eventMessage);
        }
    }
}
