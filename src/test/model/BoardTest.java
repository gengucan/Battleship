package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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

    @Test // Test for p1 win
    public void turnTestGameWon() {
        boolean[] testSet = new boolean[1];
        testSet[0] = false;
        testBoard1.addShip(1, 0, 0, 0);
        testBoard2.addShip(1,0,0,0);
        assertFalse(testBoard1.addShip(1,0,0,0,testSet));
        assertFalse(testBoard1.addShip(1,0,0,1,testSet));
        assertTrue(testBoard2.turn(0, 0));
    }

    @Test // Test for p2 win
    public void turnTestGameWonP2() {
        testBoard1.addShip(1, 0, 0, 0);
        testBoard2.addShip(1,0,0,0);
        assertFalse(testBoard1.turn(4, 4));
        assertTrue(testBoard2.turn(0,0));
    }

    @Test // Test for continuing past the first turn (full loop)
    public void turnTestGameCont(){
        testBoard1.addShip(1, 0, 0, 0);
        testBoard2.addShip(1,0,0,0);
        assertFalse(testBoard1.turn(1,1));
        assertEquals("1, 1", testBoard1.checkForSingleMiss());
    }

    @Test // Test for sinking one ship and having the game not end
    public void turnTestSinkOne() {
        testBoard1.addShip(1, 0, 0, 0);
        testBoard1.addShip(1, 7,7,0);
        testBoard2.addShip(1,0,0,0);
        assertEquals(2, testBoard1.getShipsRemaining());
        assertFalse(testBoard1.turn(0,0));
        assertEquals(1, testBoard1.getShipsRemaining());
    }

    @Test // Testing to ensure the game board puts a ship in the correct spot in both arrays
    public void addShipTest(){
        testBoard1.addShip(1,0,0,0);
        assertEquals(1, testBoard1.getShipsRemaining()); // test for ships array
        assertEquals("0, 0", testBoard1.checkForSingleShip()); // test for board array
    }

    @Test // Test for ship hit but not sunk
    public void hitButNotSunkTest() {
        testBoard1.addShip(2, 0,0, 1);
        testBoard2.addShip(1, 0,0,1);
        assertEquals(1, testBoard1.getShipsRemaining());
        assertEquals(1, testBoard2.getShipsRemaining());
        assertFalse(testBoard1.turn(0,0));
        assertEquals(1, testBoard1.getShipsRemaining());
        assertEquals(1, testBoard2.getShipsRemaining());
        assertFalse(testBoard2.turn(4,3));
        assertEquals(1, testBoard1.getShipsRemaining());
        assertEquals(1, testBoard2.getShipsRemaining());
        assertTrue(testBoard1.turn(1,0));
        assertEquals(0, testBoard1.getShipsRemaining());
        assertEquals(1, testBoard2.getShipsRemaining());
    }

    @Test
    public void badShips() {
        testBoard1.addShip(1,0,0,0);
        assertFalse(testBoard1.addShip(1,0,0,0));
        assertFalse(testBoard1.addShip(1,0,0,1));
    }

    @Test
    public void logTest() {
        EventLog.getInstance().clear();
        testBoard1.addShip(1,0,0,0);
        testBoard1.addShip(1,1,1,0);

        List<String> console = new ArrayList<>();

        for (Event e: EventLog.getInstance()) {
            console.add(e.getDescription());
        }
        assertEquals("Event log cleared.", console.get(0));
        assertEquals("New ship added at x = 0 y = 0", console.get(1));
        assertEquals("New ship added at x = 1 y = 1", console.get(2));
    }
}
