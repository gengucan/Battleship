package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/*
public class BoardTest {
    public Board testBoard1 = new Board();
    public Board testBoard2 = new Board();

    public Ship testShip1;
    public Ship testShip2;
    public Ship testShip3;
    public Ship testShip4;

    @BeforeEach // need to give the Ships arr the board test ships
    public void createTestShips() {


        Ship testShip1 = new Ship(1, 0, 0, 0); // Ship of size 1 @ (0, 0)
        Ship testShip2 = new Ship(8, 0, 0, 0); // Ship of size 8 @ (0, 0) -> (0, 7)
        Ship testShip3 = new Ship(1, 0, 0, 0); // Ship of size 1 @ (0, 0)
        Ship testShip4 = new Ship(1, 0, 0, 0); // Ship of size 1 @ (0, 0)

        testBoard1.setShips(testShip1);
        testBoard1.setShips(testShip2);
        testBoard1.setShips(testShip3);
        testBoard1.setShips(testShip4);

        testBoard2.setShips(testShip1);
    }

    @Test
    public void shipCountLimitTest() {

        // should cause the loop to fail; how do we test this?
        //assertEquals(4, testBoard1.getParsedPlayerChoiceShipCount());
        //assertEquals(1, testBoard2.getParsedPlayerChoiceShipCount());

    }

    @Test
    public void validateShipTest() {

    }
}

/*
testBoard.setParsedPlayerChoiceSize(1);
        testBoard.setParsedPlayerChoiceOri(0);
        testBoard.setParsedPlayerChoiceX(0);
        testBoard.setParsedPlayerChoiceY(0);
 */