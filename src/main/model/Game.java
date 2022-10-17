package model;

public class Game {
    Board board1;
    Board board2;

    //EFFECTS: Creates a new Game with two players
    public Game() {
        board1 = new Board();
        board2 = new Board();
    }

    // Returns board1
    public Board getBoard1() {
        return board1;
    }

    //EFFECTS: Returns board2
    public Board getBoard2() {
        return board2;
    }
}
