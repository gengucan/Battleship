package model;

import org.json.JSONObject;
import persistence.Writable;

//Represents a ship with size and coordinates (x, y) that correspond to the board
public class Ship implements Writable {
    private int size;
    private int[] coords;
    private int hits;

    private int coordX;
    private int coordY;
    private int dir;

    //REQUIRES: s is nonzero, but less than 9
    //          x and y are between 0 and 7 inclusively
    //          dir is either 1 or 0
    //MODIFIES: this
    //EFFECTS: create Ship with width w at coordinates x (main coordinate)
    public Ship(int s, int x, int y, int dir) { // test to ensure sqHit+sqRemain always = width
        size = s;

        coordX = x;
        coordY = y;
        this.dir = dir;


        // this will let us access the arrays as if they were side by side
        int linearized = (y * 8) + x;
        coords = new int[s];

        //True = horizontal
        if (dir == 1) {
            for (int i = 0; i < coords.length; i++) {
                coords[i] = linearized + i; //
            }
        } else if (dir == 0) {
            for (int i = 0; i < coords.length; i++) {
                coords[i] = linearized + (i * 8);
            }
        }
    }

    //REQUIRES: x and y are between 0 and 7 inclusively
    //MODIFIES: this
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

//    public int getCoordX() {
//        return coordX;
//    }
//
//    public int getCoordY() {
//        return coordY;
//    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("size", size);
        json.put("coordX", coordX);
        json.put("coordY", coordY);
        json.put("dir", dir);
        return json;
    }
}
