package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    @Test
    public void gameConstructorTest() {
        Game testGame = new Game();

        assertNotNull(testGame.board1);
        assertNotNull(testGame.board2);
    }
}
