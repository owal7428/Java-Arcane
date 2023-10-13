package ooad.arcane.Utility;

import java.util.ArrayList;

public interface Subject {
    ArrayList<Observer> observers = new ArrayList<>();

    default void addObserver(Observer observer) {
        observers.add(observer);
    }

    default void removeObserver(Observer observer) {
        observers.add(observer);
    }

    default void notifyObservers(String event) {
        for (Observer observer : observers)
            observer.Update(event);
    }
}
