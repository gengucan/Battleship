package persistence;

import model.Board;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReadTest {

    @Test
    void testReadNonExistentFile() {
        JsonRead jsonRead = new JsonRead("data/unknown");
        try {
            Board b = jsonRead.read();
            fail("IOExcpetion should have run");
        } catch (IOException e) {
            // this will pass the test
        }
    }

    @Test
    void testReadEmptyBoard() {
        JsonRead jsonRead = new JsonRead("data/testReadEmptyGame.json");
        try {
            Board b = jsonRead.read();
            assertEquals(0, b.getShips().size());
        } catch (IOException e) {
            fail("Did not read properly");
        }
    }

    @Test
    void testReadSomeBoard() {
        JsonRead jsonRead = new JsonRead("data/testReadSomeGame.json");

        try {
            Board b = jsonRead.read();
            b.setSunken(3);
            assertEquals(1, b.getShips().size());
            assertEquals(-2, b.getShipsRemaining()); // getShipsRemaining is getShips().size() - sunken
        } catch (IOException e) {
            fail("Could not read file");
        }
    }
}
