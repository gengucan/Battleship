package ui;

import java.util.Scanner;

public class UserInput {
    Scanner user = new Scanner(System.in);

    public UserInput() {
    }

    public int userInputCoord() {
        int coord;
        while (true) {
            coord = getInput();

            if (coord >= 0 && coord < 8) {
                return coord;
            }
        }
    }

    public int userInputSize() { //  parsedPlayerChoiceX + parsedPlayerChoiceSize > 8
        int size;
        while (true) {

            size = getInput();

            // Checks for valid size
            if (size > 0 && size < 9) {
                return size;
            }
        }
    }

    public int userInputDir() {
        int dir;
        while (true) {
            dir = getInput();

            // Check for valid orientation (0 or 1)
            if (dir == 0 || dir == 1) {
                return dir;
            }
        }
    }

    //EFFECTS: Ensures the user does not input an invalid amount of ships
    public int userInputShipCount() {
        // Can only break the loop once a number between 1 and 3 is entered
        while (true) {

            String playerChoiceShipCount = user.nextLine();
            int count = Integer.parseInt(playerChoiceShipCount);

            if (count < 3 && count > 0) {
                return count;
            }
        }
    }

    //EFFECTS: Prompts the user to enter input for the given field
    private int getInput() {
        String input = user.nextLine();
        return Integer.parseInt(input);
    }
}
