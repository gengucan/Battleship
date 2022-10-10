package model;

//EFFECTS: Creates a player with a board
public class Player {
    private Board b1;

    public Player() {
        b1 = new Board();
    }

    public Board getB1() {
        return b1;
    }

//    //EFFECTS: Prints the user a visualization of the board and prompts them for the next coordinate to scout
    public boolean startTurn(int x, int y) {
        return b1.turn(x,y);
    }


}
