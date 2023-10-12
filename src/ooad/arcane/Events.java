package ooad.arcane;
import java.util.ArrayList;
import java.util.List;

public class Events {
  
    private List<Event> events;


    public Events() {
        events = new ArrayList<>();
    }

    public List<Event> getEvents() {
        return this.events;
    }

    public void addEvent(Event event) {
        events.add(event);
    }
}