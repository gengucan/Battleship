package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class BoardTest {
    public Board testBoard1 = new Board();
    public Board testBoard2 = new Board();

    @Test // Tests the ships + sunken values through shipsRemaining
    public void addShipToBoardConstructorTest() {

        assertEquals(0, testBoard1.getShipsRemaining());
        testBoard1.addShip(1, 0, 0, 0);
        assertEquals(1, testBoard1.getShipsRemaining());
//        testBoard1.addShip(1, 0, 0, 0);
//        testBoard1.addShip(1, 0, 0, 0);
//        testBoard1.addShip(1, 0, 0, 0);

    }

    @Test // Tests the ships + sunken values through shipsRemaining
    public void boardConstructorTest() {

        assertEquals(0, testBoard1.getBoardArray());

    }
    @Test
    public void turnTestGameWon() {
        //create ships then test the t/f
        testBoard1.addShip(1, 0, 0, 0);
        assertTrue(testBoard2.turn(0, 0));
    }

    @Test
    public void turnTestGameCont(){
        testBoard1.addShip(1, 0, 0, 0);
        assertFalse(testBoard1.turn(1,1));
    }

    @Test // testing to ensure the game board puts a ship in the correct spot on the array
    public void addShipTest(){
        testBoard1.addShip(1,0,0,0);
        assertEquals(0,testBoard1.getBoardArray()); // change 0 to the actual 2d array
        //assertEquals();
    }

}

/*
testBoard.setParsedPlayerChoiceSize(1);
        testBoard.setParsedPlayerChoiceOri(0);
        testBoard.setParsedPlayerChoiceX(0);
        testBoard.setParsedPlayerChoiceY(0);
 */