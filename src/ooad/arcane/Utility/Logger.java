package ooad.arcane.Utility;

import java.io.FileWriter;
import java.io.IOException;

public class Logger implements Observer {
    public void writeEvents(int turn) {
        String filename = "Logger-" + turn + ".txt";

        // Wrap file i/o in try catch block to avoid errors
        try (FileWriter writer = new FileWriter(filename)) {
            for (String event : events)
                writer.write(event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
