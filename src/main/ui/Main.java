package ui;

import model.Game;

public class Main { // create a printBoard for setup with 1's showing to user

    //EFFECTS: Prints the ships remaining + the 2D array to the console using the following mapping:
    // ? = Has not been checked by player
    // x = Checked by player, missed
    // ! = Checked by player, hit
    private static void printBoard(int[][] boardArray, int shipsRemaining) {
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
            System.out.println(); // creates new line after every 8 prints (to create an 8x8 board)
        }
        System.out.println("You have " + shipsRemaining + " ships left to find");
        System.out.println();
    }

    private static boolean runTurn1(Game g, UserInput u) {
        // Player 1 Turn
        System.out.println("Player 1 turn:");

        printBoard(g.getBoard2().getBoardArray(), g.getBoard2().getShipsRemaining());

        System.out.println("What X coordinate do you want to scout?");
        int x = u.userInputCoord();

        System.out.println("What Y coordinate do you want to scout?");
        int y = u.userInputCoord();

        if (g.getBoard2().turn(x, y)) {
            System.out.println("Player 1 wins!");
            return true;
        }
        System.out.println("Player 1 Scouting Report");
        printBoard(g.getBoard2().getBoardArray(), g.getBoard2().getShipsRemaining());

        return false;
    }

    private static boolean runTurn2(Game g, UserInput u) {
        //Player 2 Turn
        System.out.println("Player 2 turn:");
        printBoard(g.getBoard1().getBoardArray(), g.getBoard1().getShipsRemaining());

        System.out.println("What X coordinate do you want to scout?");
        int x = u.userInputCoord();

        System.out.println("What Y coordinate do you want to scout?");
        int y = u.userInputCoord();

        if (g.getBoard1().turn(x, y)) {
            System.out.println("Player 2 wins!");
            return true;
        }
        System.out.println("Player 2 Scouting Report:");
        printBoard(g.getBoard1().getBoardArray(), g.getBoard1().getShipsRemaining());
        return false;
    }

    private static void startUp() {
        System.out.println();
        System.out.println("Welcome to Battleship!");
        System.out.println();
        System.out.println("Please only input integers like '1' and '3' anywhere where input is required.");
        System.out.println("The size of the board is 8x8, and the square are accessed by using zero-based indexing.");
        System.out.println("Therefore, all inputs should be between 0-7.");
        System.out.println("GLHF!");
        System.out.println();
    }

    private static void turnSetup(int p) {
        System.out.println("PLAYER " + p + " Setup:");
        System.out.println();
        System.out.println("How many ships would you like between 1-3?");
    }

    public static void main(String[] args) {
        Game game1 = new Game();

        UserInput userInput = new UserInput();

        // Start up
        startUp();

        turnSetup(1);

        int shipCount = userInput.userInputShipCount();
        int counter = 0;
        while (counter < shipCount) {
            System.out.println("How big do you want the ship to be?");
            int size = userInput.userInputSize();

            System.out.println("Which direction do you want this ship facing? '0' = Vertical and '1' = Horizontal");
            int dir = userInput.userInputDir();

            System.out.println("What X coordinate do you want this to be on?");
            int x = userInput.userInputCoord();

            System.out.println("What Y coordinate do you want this to be on?");
            int y = userInput.userInputCoord();

            if (game1.getBoard1().addShip(size, x, y, dir)) {
                counter++;
                System.out.println("Ship was added successfully");
            } else {
                System.out.println("You are overlapping ships try again");
            }
        }

        turnSetup(2);

        shipCount = userInput.userInputShipCount();
        counter = 0;

        while (counter < shipCount) {
            System.out.println("How big do you want the ship to be?");
            int size = userInput.userInputSize();

            System.out.println("Which direction do you want this ship facing? '0' = Vertical and '1' = Horizontal");
            int dir = userInput.userInputDir();

            System.out.println("What X coordinate do you want this to be on?");
            int x = userInput.userInputCoord();

            System.out.println("What Y coordinate do you want this to be on?");
            int y = userInput.userInputCoord();

            if (game1.getBoard2().addShip(size, x, y, dir)) {
                counter++;
                System.out.println("Ship was added successfully");
            } else {
                System.out.println("You are overlapping ships try again");
            }
        }

        while (true) {
            if (runTurn1(game1, userInput) || runTurn2(game1, userInput)) {
                break;
            }
        }
    }
}