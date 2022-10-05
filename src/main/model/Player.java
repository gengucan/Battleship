package model;

import java.util.Scanner; //https://www.w3schools.com/java/java_user_input.asp

public class Player {
    private int shipsRemaining;
    private boolean isGameOver;

    public Player() {
        isGameOver = false;

        checkGameOver();
    }

    public void checkGameOver() {
        if (shipsRemaining == 0) {
            isGameOver = true;
        }
    }

    public boolean getIsGameOver() {
        return isGameOver;
    }

    public void select() {
        Scanner user = new Scanner(System.in);
        System.out.println("Where would you like to scout?"); // save this val

        String playerChoice = user.nextLine(); // need if statement next to check for which hit/miss lists to print
        //System.out.println(); // this should just print the hit and miss lists


    }
}
