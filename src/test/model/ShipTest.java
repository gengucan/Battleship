package model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void logTest() {
        EventLog.getInstance().clear();
        Ship testShip = new Ship(1, 0, 0, 0);

        List<String> console = new ArrayList<>();

        assertTrue(testShip.isHit(0, 0));
        assertTrue(testShip.isSunk());

        for (Event e: EventLog.getInstance()) {
            console.add(e.getDescription());
        }

        assertEquals("Event log cleared.", console.get(0));
        assertEquals("A ship has been sunk", console.get(1));

    }
}