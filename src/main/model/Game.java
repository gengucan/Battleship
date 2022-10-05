package model;

public class Game {
    //private int turnSelect = 0;

    public Game() { // main creates an instance of game?
        int turnSelect = 0;

        Board board1 = new Board();
        Board board2 = new Board();

        Player player1 = new Player();
        Player player2 = new Player();

    }

    public void playerTurn(Player p) {
        p.select();

    }

    public void nextPlayerTurn(int turn, Player p1, Player p2) {
        if ((turn % 2) != 0) {
            p1.select();
            ++turn;
        } else if ((turn % 2) == 0) {
            p1.select();
            ++turn;
        }
    }

    //EFFECTS: Checks if either player has lost the game, if not start the next turn sequence
    public void gameOver(int turn, Player p1, Player p2) { // 2 helper fn?
        if (p1.getIsGameOver() == true) {
            end(p2);
        } else if (p2.getIsGameOver() == true) {
            end(p1);
        } else {
            nextPlayerTurn(turn, p1, p2); // need to find a way to preserve the turn counter
        }
    }

    //
    public void end(Player winner) {
        System.out.println("The game is over, " + winner + "has won!");
        // exit app
    }

}
