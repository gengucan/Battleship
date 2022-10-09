package model;

public class Game {
    Player player1;
    Player player2;

    //EFFECTS: Creates a new Game with two players
    public Game() {
        System.out.println("");
        System.out.println("Welcome to Battleship!");
        System.out.println("");
        System.out.println("Please only input integers like '1' and '3' anywhere where input is required.");
        System.out.println("The size of the board is 8x8, and the square are accessed by using zero-based indexing.");
        System.out.println("Therefore, all inputs should be between 0-7.");
        System.out.println("GLHF!");
        System.out.println("");

        player1 = new Player();
        player2 = new Player();
    }

    //EFFECTS: Starts each player's turn and ends the game if either player runs out of ships
    public void startGame() {
        while (true) {
            System.out.println("Player 1 turn:"); //
            if (player2.startTurn()) {
                System.out.println("Player 1 wins!");
                break;
            }
            System.out.println("Player 2 turn:");
            if (player1.startTurn()) {
                System.out.println("Player 2 wins!");
                break;
            }
        }
    }
}
