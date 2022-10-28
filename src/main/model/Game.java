package model;

//Represents the game with two boards, one for each player
public class Game {
    Board board1;
    Board board2;

    //EFFECTS: Creates a new Game with two players
    public Game() {
        board1 = new Board();
        board2 = new Board();
    }

    //EFFECTS: Returns board1
    public Board getBoard1() {
        return board1;
    }

    //EFFECTS: Returns board2
    public Board getBoard2() {
        return board2;
    }

    public void setBoard1(Board b) {
        board1 = b;
    }

    public void setBoard2(Board b) {
        board2 = b;
    }
}
