package model;

import java.util.Scanner;


public class Board {
    // 2D array to represent the board
    private int[][] boardArray;
    // Array of ships
    private Ship[] ships;
    private int sunken;
    Scanner user = new Scanner(System.in);

    private int parsedPlayerChoiceSize;
    private int parsedPlayerChoiceX;
    private int parsedPlayerChoiceY;
    private int parsedPlayerChoiceOri;
    private int parsedPlayerChoiceShipCount;

    //EFFECTS: Creates a Board with 0 sunken ships, and an 8x8 array to represent the tiles of the board
    public Board() {
        sunken = 0;
        boardArray = new int[8][8];

        // Set all values to 0 (unchecked)
        for (int i = 0; i < 0; i++) {
            for (int j = 0; j < 0; j++) {
                boardArray[i][j] = 0;
            }
        }
        // ask user for amount of ships
        shipCountLimit();
        ships = new Ship[parsedPlayerChoiceShipCount];

        // creates ships of user's desired dimensions
        for (int i = 0; i < ships.length; i++) {

            playerChoice();

// all checks on valid/invalid ships must occur here latest

            ships[i] = new Ship(parsedPlayerChoiceSize, parsedPlayerChoiceX,
                    parsedPlayerChoiceY, parsedPlayerChoiceOri);
            if (parsedPlayerChoiceOri == 1) {
                for (int j = parsedPlayerChoiceX; j < (parsedPlayerChoiceX
                        + parsedPlayerChoiceSize); j++) {
                    boardArray[parsedPlayerChoiceX][parsedPlayerChoiceY] = 1;
                }
            } else {
                for (int k = parsedPlayerChoiceY; k < (parsedPlayerChoiceY + parsedPlayerChoiceX); k++) {
                    boardArray[parsedPlayerChoiceX][parsedPlayerChoiceY] = 1;
                }
            }
        }
        // spam new lines so the user can't see the other user's ships
    }

    //EFFECTS: Prompts the user to select the properties of each ship until the ship array is full
    public void playerChoice() {
        System.out.println("How big do you want the ship to be?");
        parsedPlayerChoiceSize = getInput();

        System.out.println("What X coordinate do you want this to be on?");
        parsedPlayerChoiceX = getInput();

        System.out.println("What Y coordinate do you want this to be on?");
        parsedPlayerChoiceY = getInput();

        System.out.println("Do you want this to be horizontal? (type without quotations: "
                + "'1' for horizontal or '0' for vertical)");
        parsedPlayerChoiceOri = getInput();

        // Ensures user input 'true' or 'false' into the orientation prompt so that the value can be properly parsed
        if (parsedPlayerChoiceOri > 1) {
            playerChoice(); // this is tripping on every run
        }
        validateShip();
    }

    //EFFECTS: Prompts the user to enter input for the given field
    private int getInput() {
        String input = user.nextLine();
        return Integer.parseInt(input);
    }

    //EFFECTS: Ensures the user does not input an invalid amount of ships
    public void shipCountLimit() {
        // Can only break the loop once a number between 1 and 3 is entered
        while (true) {
            System.out.println("How many ships would you like between 1-3?");
            String playerChoiceShipCount = user.nextLine();
            parsedPlayerChoiceShipCount = Integer.parseInt(playerChoiceShipCount);

            if (parsedPlayerChoiceShipCount < 3 && parsedPlayerChoiceShipCount > 0) {
                break;
            }
        }
    }

    //EFFECTS: Checks the given ship to ensure it conforms to the rules in the context of the current board
    public void validateShip() {
        // Checks to ensure ship is within the boundaries
        if (parsedPlayerChoiceX < 0 || parsedPlayerChoiceY < 0 || parsedPlayerChoiceX > 8 || parsedPlayerChoiceY > 8) {
            System.out.println("This ship's starting coordinate is outside of the boundaries, try this ship again");
            playerChoice();
        } else if (parsedPlayerChoiceX + parsedPlayerChoiceSize > 8
                || parsedPlayerChoiceY + parsedPlayerChoiceSize > 8) { // Checks tail end
            System.out.println("This ship's tail is outside of the boundaries, try this ship again");
            playerChoice();
        }

        // Checks to see if all squares needed are 0 (no overlapping ships)
        if (parsedPlayerChoiceOri > 1) {
            for (int i = parsedPlayerChoiceX; i < parsedPlayerChoiceX + parsedPlayerChoiceSize; i++) {
                if (boardArray[i][parsedPlayerChoiceY] != 0) {
                    System.out.println("You are overlapping ships try again");
                    playerChoice();
                }
            }
        } else {
            for (int j = parsedPlayerChoiceY; j < parsedPlayerChoiceY + parsedPlayerChoiceSize; j++) {
                if (boardArray[parsedPlayerChoiceX][j] != 0) {
                    System.out.println("You are overlapping ships try again");
                    playerChoice();
                }
            }
        }
    }

    //MODIFIES: boardArray, sunken
    //EFFECTS: Checks the scouted tile and changes it depending on if it was hit or missed;
    //         Returns true if all ships are sunk
    public boolean turn(int x, int y) {
        // Check to see if inside the board
        if (x >= 0 && x < 8 && y >= 0 && y < 8) {

            // Check to see if tile has been checked + current state of the tile
            if (boardArray[x][y] == 0) {
                boardArray[x][y] = 2; // miss
            } else if (boardArray[x][y] == 1) {
                boardArray[x][y] = 3;
                boolean foundShip = false;
                int counter = 0;

                // Find out which ship was hit
                while (!foundShip) {
                    foundShip = ships[counter].isHit(x, y);
                    counter++;
                    if (counter >= ships.length) {
                        break;
                    }
                }

                // Checks if all the ships have been sunk
                if (ships[--counter].isSunk()) {
                    sunken++;
                    if (sunken == ships.length) {
                        return true;
                    }
                }
            } else {
                System.out.println("Trying out some new tech? (You already checked this....)");
            }
        } else {
            System.out.println("You are scouting outside of the board....");
        }
        return false;
    }


    //EFFECTS: Prints the ships remaining + the 2D array to the console using the following mapping:
    // ? = Has not been checked by player
    // x = Checked by player, missed
    // ! = Checked by player, hit
    public void printBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((boardArray[i][j] == 0) || (boardArray[i][j] == 1)) {
                    System.out.print("?"); // this prints to board -> do we need ln?
                } else if (boardArray[i][j] == 2) {
                    System.out.print("x");
                } else if (boardArray[i][j] == 3) {
                    System.out.print("!");
                }
                System.out.print("\t"); // prints the spacing
            }
            System.out.println(""); // creates new line after every 8 prints (to create an 8x8 board)
        }

        int shipsRemaining = ships.length - sunken;
        System.out.println("You have " + shipsRemaining + " ships left to find");
        System.out.println("");
    }
}


// OLD VERSION OF shipCountLimit
/*
    public void shipCountLimit() {
        System.out.println("How many ships would you like between 1-3?");
        String playerChoiceShipCount = user.nextLine();
        parsedPlayerChoiceShipCount = Integer.parseInt(playerChoiceShipCount);

        if (parsedPlayerChoiceShipCount > 3 || parsedPlayerChoiceShipCount < 0) {
            shipCountLimit(); // need to change this
       }
 */