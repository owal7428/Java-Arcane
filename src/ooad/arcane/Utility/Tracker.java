package ooad.arcane.Utility;

import java.util.LinkedList;
import java.util.Queue;

public class Tracker implements Observer {
    Queue<String> events = new LinkedList<>();

    @Override
    public void Update(String event) {
        events.add(event + "\n");
    }
}
