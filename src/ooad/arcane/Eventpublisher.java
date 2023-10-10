package ooad.arcane;

import java.util.ArrayList;

public class Eventpublisher implements Subject {
    private ArrayList<Observer> observers;

    public Eventpublisher() {
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String eventMessage) {
        for (Observer observer : observers) {
            observer.update(eventMessage);
        }
    }

    // Convenience method to publish an event.
    public void publishEvent(String eventMessage) {
        notifyObservers(eventMessage);
    }
}