package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class BoardTest {
    public Board testBoard1 = new Board();
    public Board testBoard2 = new Board();

    @Test // Tests adding and ships and repeated ships
    public void addShipToBoardConstructorTest() {
        assertEquals(0, testBoard1.getShipsRemaining());
        testBoard1.addShip(1, 0, 0, 0);
        assertEquals(1, testBoard1.getShipsRemaining());
        testBoard1.addShip(1, 0, 0, 0);
        assertEquals(1, testBoard1.getShipsRemaining());
    }

    @Test // Tests the ships + sunken values through shipsRemaining
    public void boardConstructorTest() {
        assertEquals("error", testBoard1.checkForSingleShip()); // only zeros, no ships in 2d array
        assertEquals(0, testBoard1.getShipsRemaining()); // no ships remain (no ships were set)
    }

    @Test
    public void turnTestGameWon() {
        testBoard1.addShip(1, 0, 0, 0);
        testBoard2.addShip(1,0,0,0);
        assertTrue(testBoard2.turn(0, 0));
    }

    @Test
    public void turnTestGameCont(){
        testBoard1.addShip(1, 0, 0, 0);
        testBoard2.addShip(1,0,0,0);
        assertFalse(testBoard1.turn(1,1));
        assertEquals("1, 1", testBoard1.checkForSingleMiss());
    }

    @Test // testing to ensure the game board puts a ship in the correct spot in both arrays
    public void addShipTest(){
        testBoard1.addShip(1,0,0,0);
        assertEquals(1, testBoard1.getShipsRemaining()); // test for ships array
        assertEquals("0, 0", testBoard1.checkForSingleShip()); // test for board array
    }

}
