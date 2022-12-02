package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

//Represents the info from an individual board: the 2D array that represents the board, an array that contains the ships
// on the board and the amount of ships that have been sunk
public class Board implements Writable {
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
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boardArray[i][j] = 0;
            }
        }
    }

    //REQUIRES: x and y are between 0 and 7 inclusively
    //MODIFIES: this
    //EFFECTS: Checks the scouted tile and changes it depending on if it was hit or missed;
    //         Returns true if all ships are sunk
    public boolean turn(int x, int y) {
        // Check to see if tile has been checked + current state of the tile
        if (boardArray[y][x] == 0) {
            boardArray[y][x] = 2; // miss
        } else if (boardArray[y][x] == 1) { // flipped here
            boardArray[y][x] = 3;
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
            if (ships.get(--counter).isSunk()) {
                sunken++;
                //ships.remove(counter);
                if (sunken == ships.size()) {
                    return true;
                }
            }
        }
        return false;
    }

    //REQUIRES: dir is either 0 or 1; s is non-zero, x and y are between 0 and 7 inclusively
    //MODIFIES: this
    //EFFECTS: Adds a ship of the given size, x coord, y coord, and direction to the 2D array and the ships array
    // Note that the x and y are flipped in terms of the 2D array
    public boolean addShip(int s, int x, int y, int dir) {
        if (dir == 1) { // horizontal
            for (int i = x; i < x + s; i++) {
                if (boardArray[y][i] != 0) {
                    return false;
                }
            }
            for (int j = x; j < (x + s); j++) {
                boardArray[y][j] = 1;
            }
        } else { // vertical
            for (int j = y; j < y + s; j++) {
                if (boardArray[j][x] != 0) {
                    return false;
                }
            }
            for (int k = y; k < (y + s); k++) {
                boardArray[k][x] = 1;
            }
        }
        Ship ship = new Ship(s, x, y, dir);
        ships.add(ship);

        EventLog.getInstance().logEvent(new Event("New ship added at x = " + x + " y = " + y));

        return true;
    }

    //REQUIRES: dir is either 0 or 1; s is non-zero, x and y are between 0 and 7 inclusively
    //MODIFIES: this
    //EFFECTS: Adds a ship of the given size, x coord, y coord, and direction to the 2D array and the ships array
    // Note that the x and y are flipped in terms of the 2D array
    // this version of the method will track which tiles have been hit on the ship
    public boolean addShip(int s, int x, int y, int dir, boolean[] hits) {
        if (dir == 1) { // horizontal
            for (int i = x; i < x + s; i++) {
                if (boardArray[y][i] != 0) {
                    return false;
                }
            }
            for (int j = x; j < (x + s); j++) {
                boardArray[y][j] = 1;
            }
        } else { // vertical
            for (int j = y; j < y + s; j++) {
                if (boardArray[j][x] != 0) {
                    return false;
                }
            }
            for (int k = y; k < (y + s); k++) {
                boardArray[k][x] = 1;
            }
        }
        Ship ship = new Ship(s, x, y, dir, hits);
        ships.add(ship);

        EventLog.getInstance().logEvent(new Event("New ship added at x = " + x + " y = " + y));

        return true;
    }

    //EFFECTS: Returns private variable boardArray
    public int[][] getBoardArray() {
        return boardArray;
    }

    //EFFECTS: Returns the number of ships remaining
    public int getShipsRemaining() {
        return ships.size() - sunken;
    }

    //EFFECTS: Returns the ships array
    public ArrayList<Ship> getShips() {
        return ships;
    }

    //EFFECTS: Sets board array to the param
    public void setBoardArray(int[][] array) {
        boardArray = array;
    }

    //EFFECTS: Sets the value of sunken to the param
    public void setSunken(int sunk) {
        sunken = sunk;
    }

    //EFFECTS: Places key/value pairs into the json file
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("boardArray", boardArray);
        json.put("ships", shipsToJson());
        json.put("sunken", sunken);
        return json;
    }

    //EFFECTS: Translates my code to be compatible with json
    private JSONArray shipsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Ship s: ships) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }

    //TESTING ONLY BELOW!!!

    //EFFECTS: returns the coordinates of the first ship encountered
    public String checkForSingleShip() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (boardArray[i][j] == 1) {
                    return ("" + i + ", " + j);
                }
            }
        }
        return "error";
    }

    //EFFECTS: returns the coordinates of the first miss square encountered
    public String checkForSingleMiss() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (boardArray[i][j] == 2) {
                    return ("" + i + ", " + j);
                }
            }
        }
        return "error";
    }
}


