package ui;

import javax.swing.*;

//Represents the user inputs and checks the user inputs for consistency with the rules of Battleship
public class UserInput {

    //EFFECTS: Creates a UserInput object that allows us to access the methods in UserInput
    public UserInput() {
    }

    //EFFECTS: Checks if given coord is within the boundaries
    public int setupCoordX() {
        int coord;
        String input;


        while (true) {
            input = JOptionPane.showInputDialog("What x coordinate do you want this ship on?");

            try {
                coord = Integer.parseInt(input);

                if (coord >= 0 && coord < 8) {
                    return coord;
                }
            } catch (NumberFormatException e) {
                // do nothing; will redo text prompt
            }
        }
    }

    //EFFECTS: Checks if given coord is within the boundaries
    public int setupCoordY() {
        int coord;
        String input;

        while (true) {
            input = JOptionPane.showInputDialog("What y coordinate do you want this ship on?");

            try {
                coord = Integer.parseInt(input);

                if (coord >= 0 && coord < 8) {
                    return coord;
                }
            } catch (NumberFormatException e) {
                // do nothing; will redo text prompt
            }
        }
    }

    //EFFECTS: Checks if given size is not bigger than the length/width of the board
    public int userInputSize() {
        int size;
        String input;

        while (true) {
            input = JOptionPane.showInputDialog("What size would you like this ship to be?");

            try {
                size = Integer.parseInt(input);

                // Checks for valid size
                if (size > 0 && size < 9) {
                    return size;
                }
            } catch (NumberFormatException e) {
                // do nothing; will redo text prompt
            }
        }
    }

    //EFFECTS: Checks if given dir is either 0 or 1
    public int userInputDir() {
        int dir;
        String input;

        while (true) {
            input = JOptionPane.showInputDialog("Which direction do you want this ship facing? '0' = Vertical and"
                    + "'1' = Horizontal");

            try {
                dir = Integer.parseInt(input);

                // Check for valid orientation (0 or 1)
                if (dir == 0 || dir == 1) {
                    return dir;
                }
            } catch (NumberFormatException e) {
                // do nothing; will redo text prompt
            }
        }
    }

    //EFFECTS: Ensures the user does not input an invalid amount of ships
    public int userInputShipCount() {
        // Can only break the loop once a number between 1 and 3 is entered
        while (true) {
            String playerChoiceShipCount = JOptionPane.showInputDialog("How many ships would you like?");

            try {
                int count = Integer.parseInt(playerChoiceShipCount);

                if (count < 4 && count > 0) {
                    return count;
                }
            } catch (NumberFormatException e) {
                // do nothing; will redo text prompt
            }
        }
    }
}
