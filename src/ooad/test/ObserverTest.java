package ooad.test;

import ooad.arcane.Utility.Logger;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

public class ObserverTest {
    @Test
    public void testLoggerWrite() {
        Logger logger = new Logger();
        logger.Update("This is a test.");
        logger.Update("Newline.");
        logger.writeEvents(0);

        try(FileReader reader = new FileReader("Logger-0.txt")) {
            StringBuilder temp = new StringBuilder();
            int i;
            while((i=reader.read())!=-1)
                temp.append((char) i);
            String temp_str = temp.toString();
            assert (temp_str.equals("This is a test.\nNewline.\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
