package ooad.arcane.Utility;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public interface Observer {
    Queue<String> events = new LinkedList<>();
    default void Update(String event) {
        events.add(event);
    }
}
