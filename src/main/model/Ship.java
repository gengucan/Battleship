package model;

public class Ship {
    private int size;
    private int[] coords;
    private int hits;


    //EFFECTS: create Ship with width w at coordinates x (main coordinate)
    public Ship(int s, int x, int y, int orientation) { // test to ensure sqHit+sqRemain always = width
        size = s;

        // this will flatten the arrays?
        int linearized = (y * 8) + x;
        coords = new int[s];

        //True = horizontal
        if (orientation == 1) {
            for (int i = 0; i < coords.length; i++) {
                coords[i] = linearized + i; //
            }
        } else if (orientation == 0) {
            for (int i = 0; i < coords.length; i++) {
                coords[i] = linearized + (i * 8);
            }
        }

    }

    //EFFECTS: Checks to see if a ship is hit
    public boolean isHit(int x, int y) {
        int linearized = (y * 8) + x; // 8 represents the board size

        for (int i = 0; i < coords.length; i++) {
            if (linearized == coords[i]) {
                hits++;
                return true;
            }
        }

        return false;
    }

    //EFFECTS: Checks to see if the ship has been sunk
    public boolean isSunk() {
        if (hits == size) {
            return true;
        }
        return false;
    }
}
