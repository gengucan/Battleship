package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShipTest {

    @Test
    public void shipHitAndSunk() {
        Ship testShip = new Ship(1, 0, 0, 0);

        assertTrue(testShip.isHit(0, 0));
        assertTrue(testShip.isSunk());

    }

    @Test
    public void shipHitAndNotSunk() {
        Ship testShip = new Ship(2, 0, 0, 0);

        assertTrue(testShip.isHit(0, 0));
        assertFalse(testShip.isSunk());

    }

    @Test
    public void shipNotHit() {
        Ship testShip = new Ship(1, 0, 0, 0);

        assertFalse(testShip.isHit(7, 7));
        assertFalse(testShip.isSunk());

    }
}