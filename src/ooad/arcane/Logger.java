package ooad.arcane;
import java.io.FileWriter;
import java.io.IOException;
import ooad.arcane.Event;

public class Logger{
    private int turn;
    Event event;

    public Logger(int turn) {
        this.turn = turn;
    }


    // @Override
    // public void update(String eventMessage) {
        
    //      try (FileWriter writer = new FileWriter("Logger-" + turn + ".txt", true)) {
    //         writer.write(event.getDescription() + "\n");
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }
}