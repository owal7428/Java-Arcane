package ooad.arcane.Utility;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Logger implements Observer {
    Queue<String> events = new LinkedList<>();

    @Override
    public void Update(String event) {
        events.add(event + "\n");
    }

    public void writeEvents(int turn) {
        String filename = "Logger-" + turn + ".txt";

        // Wrap file i/o in try catch block to avoid errors
        try (FileWriter writer = new FileWriter(filename)) {
            while (!events.isEmpty())
                writer.write(events.remove());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
