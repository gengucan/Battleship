package model;

import java.util.ArrayList;


public class Board {
    // 2D array to represent the board
    private int[][] boardArray;
    // Array of ships
    private ArrayList<Ship> ships;
    private int sunken;


    //EFFECTS: Creates a Board with 0 sunken ships, and an 8x8 array to represent the tiles of the board
    public Board() {
        sunken = 0;
        boardArray = new int[8][8];
        ships = new ArrayList<>(1);

        // Set all values to 0 (unchecked)
        for (int i = 0; i < 0; i++) {
            for (int j = 0; j < 0; j++) {
                boardArray[i][j] = 0;
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: Checks the scouted tile and changes it depending on if it was hit or missed;
    //         Returns true if all ships are sunk
    public boolean turn(int x, int y) {
        // Check to see if tile has been checked + current state of the tile
        if (boardArray[x][y] == 0) {
            boardArray[x][y] = 2; // miss
        } else if (boardArray[x][y] == 1) {
            boardArray[x][y] = 3;
            boolean foundShip = false;
            int counter = 0;

            // Find out which ship was hit
            while (!foundShip) {
                foundShip = ships.get(counter).isHit(x, y);
                counter++;
                if (counter >= ships.size()) {
                    break;
                }
            }

            // Checks if all the ships have been sunk
            if (ships.get(--counter).isSunk()) { // use ships[--counter] for normal arr
                sunken++;
                if (sunken == ships.size()) {
                    return true;
                }
            }
        }
        return false;
    }

    //REQUIRES: dir is either 0 or 1; s is non-zero
    //MODIFIES: this
    //EFFECTS: Adds a ship of the given size, x coord, y coord, and direction to the 2D array and the ships array
    public boolean addShip(int s, int x, int y, int dir) {
        if (dir > 1) {
            for (int i = x; i < x + s; i++) {
                if (boardArray[i][y] != 0) {
                    return false;
                }
            }
            for (int j = x; j < (x + s); j++) {
                boardArray[x][y] = 1;
            }
        } else {
            for (int j = y; j < y + s; j++) {
                if (boardArray[x][j] != 0) {
                    return false;
                }
            }
            for (int k = y; k < (y + x); k++) {
                boardArray[x][y] = 1;
            }
        }
        Ship ship = new Ship(s, x, y, dir);
        ships.add(ship);
        return true;
    }

    public int[][] getBoardArray() {
        return boardArray;
    }

    public int getShipsRemaining() {
        return ships.size() - sunken;
    }
}


