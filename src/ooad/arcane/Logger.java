package ooad.arcane;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Logger implements Observer {
    private int turnNumber;
    private BufferedWriter writer;

    public Logger(int turnNumber) throws IOException {
        this.turnNumber = turnNumber;
        writer = new BufferedWriter(new FileWriter("Logger-" + turnNumber + ".txt", true));
    }

    @Override
    public void update(String eventMessage) {
        try {
            writer.write(eventMessage);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() throws IOException {
        writer.close();
    }
}