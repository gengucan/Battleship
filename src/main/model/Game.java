package model;

public class Game {
    Board board1;
    Board board2;

    //EFFECTS: Creates a new Game with two players
    public Game() {
        board1 = new Board();
        board2 = new Board();

    }

    public Board getBoard1() {
        return board1;
    }

    public Board getBoard2() {
        return board2;
    }
}
