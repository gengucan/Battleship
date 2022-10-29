package persistence;

import model.Board;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriteTest {

    @Test
    void testWriteInvalidFile() {
        try {
            Board b = new Board();
            JsonWrite jsonWrite = new JsonWrite("data/idk\0idk");
            jsonWrite.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // if we get here the test will pass
        }
    }

    @Test
    void testWriteEmptyGame() {
        try {
            Board b = new Board();
            JsonWrite jsonWrite = new JsonWrite("data/testWriteNewGame.json");
            jsonWrite.open();
            jsonWrite.write(b);
            jsonWrite.close();

            JsonRead jsonRead = new JsonRead("data/testWriteNewGame.json");
            b = jsonRead.read();
            assertEquals(0, b.getShipsRemaining());
        } catch (IOException e) {
            fail("This exception should not be run");
        }
    }

    @Test
    void testWriteSomeGame() {
        try {
            Board b = new Board();
            JsonWrite jsonWrite = new JsonWrite("data/testWriteSomeGame.json");
            b.addShip(1,0,0,0);
            b.addShip(1,1,0,0);
            jsonWrite.open();
            jsonWrite.write(b);
            jsonWrite.close();

            JsonRead jsonRead = new JsonRead("data/testWriteNewGame.json");
            b = jsonRead.read();
            assertEquals(0, b.getShipsRemaining());
        } catch (IOException e) {
            fail("This exception should not be run");
        }
    }
}
