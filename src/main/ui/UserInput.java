package ui;

import java.util.Scanner;

//Represents the user inputs and checks the user inputs for consistency with the rules of Battleship
public class UserInput {
    Scanner user = new Scanner(System.in);

    //EFFECTS: Creates a UserInput object that allows us to access the methods in UserInput
    public UserInput() {
    }


    //EFFECTS: Checks if given coord is within the boundaries
    public int userInputCoord() {
        int coord;
        while (true) {
            coord = getInput();

            if (coord >= 0 && coord < 8) {
                return coord;
            }
        }
    }

    //EFFECTS: Checks if given size is not bigger than the length/width of the board
    public int userInputSize() {
        int size;
        while (true) {

            size = getInput();

            // Checks for valid size
            if (size > 0 && size < 9) {
                return size;
            }
        }
    }

    //EFFECTS: Checks if given dir is either 0 or 1
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

            if (count < 4 && count > 0) {
                return count;
            }
        }
    }

    //EFFECTS: Prompts the user to enter input for the given field
    public int getInput() {
        String input = user.nextLine();
        return Integer.parseInt(input);
    }
}
