package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class BoardTest {
    public Board testBoard1 = new Board();

    @Test
    public void boardTest() {

        assertEquals(0, testBoard1.getShipsRemaining());
        testBoard1.addShip(1, 0, 0, 0);
        assertEquals(1, testBoard1.getShipsRemaining());

//        testBoard1.addShip(1, 0, 0, 0);
//        testBoard1.addShip(1, 0, 0, 0);
//        testBoard1.addShip(1, 0, 0, 0);

    }

    @Test
    public void turnTestGameWon() {
        //create ships then test the t/f
        testBoard1.addShip(1, 0, 0, 0);
        assertTrue(testBoard1.turn(0, 0));
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
    }

}

/*
testBoard.setParsedPlayerChoiceSize(1);
        testBoard.setParsedPlayerChoiceOri(0);
        testBoard.setParsedPlayerChoiceX(0);
        testBoard.setParsedPlayerChoiceY(0);
 */