package model;

import java.util.Scanner;

//EFFECTS: Creates a player with a board
public class Player {
    private Board b1;

    public Player() {
        b1 = new Board();
    }

    //EFFECTS: Prints the user a visualization of the board and prompts them for the next coordinate to scout
    public boolean startTurn() {
        b1.printBoard();
        Scanner user = new Scanner(System.in);

        //Prompt for x
        System.out.println("");
        System.out.println("Which x coordinate would you like to scout?");
        String playerScoutX = user.nextLine();

        //Prompt for y
        System.out.println("");
        System.out.println("Which y coordinate would you like to scout?");
        String playerScoutY = user.nextLine();

        return b1.turn(Integer.parseInt(playerScoutX),Integer.parseInt(playerScoutY));
    }


}
