package ui;

import model.Board;
import model.Game;

import persistence.JsonRead;
import persistence.JsonWrite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

// Represents a graphical user interface which is displayed using Java Swing library
public class UserInterface extends JFrame implements ActionListener { // ButtonDemoProject from docs.oracle.com

    private ArrayList<JButton> buttons = new ArrayList<>(64);

    JFrame frame;
    static Game game;
    UserInput userInput;

    JMenu menu;
    JMenuBar menuBar;
    JMenuItem menuItem;

    BoardPanel boardPanel1;
    BoardPanel boardPanel2;

    ShipsRemainingPanel shipsRemainingPanel1;
    ShipsRemainingPanel shipsRemainingPanel2;

    GridBagConstraints gridBagConstraints;

    private static final String FILE_PATH1 = "data/board1.json";
    private static JsonWrite jsonWrite1 = new JsonWrite(FILE_PATH1);
    private static JsonRead jsonRead1 = new JsonRead(FILE_PATH1);

    private static final String FILE_PATH2 = "data/board2.json";
    private static JsonWrite jsonWrite2 = new JsonWrite(FILE_PATH2);
    private static JsonRead jsonRead2 = new JsonRead(FILE_PATH2);

//EFFECTS: Creates a new user interface object
    public UserInterface() {
        game = new Game();

        setup();

        shipsRemainingPanel1 = new ShipsRemainingPanel(game.getBoard2());
        shipsRemainingPanel2 = new ShipsRemainingPanel(game.getBoard1());

        gridBagConstraints = new GridBagConstraints();

        boardPanel1 = new BoardPanel(1, game, shipsRemainingPanel1);
        boardPanel2 = new BoardPanel(2, game, shipsRemainingPanel2);

        boardPanel1.setEnemyBoardPanel(boardPanel2);
        boardPanel2.setEnemyBoardPanel(boardPanel1);

        frame = new JFrame("Battleship");
        frame.setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);

        menuBar = new JMenuBar();
        menuItem = new JMenuItem("Save");
        menu = new JMenu("Menu");
        menu.add(menuItem);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        menuItem.addActionListener(this);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.1;


        frame.add(boardPanel1.getBoard(), gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;

        frame.add(boardPanel2.getBoard(), gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weighty = 1;

        frame.add(shipsRemainingPanel1, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;

        frame.add(shipsRemainingPanel2, gridBagConstraints);

        frame.setSize(1920, 1080);
        pack();
        frame.setVisible(true);
    }

    //EFFECTS: saves game when the button is pressed on player 1's turn
    @Override
    public void actionPerformed(ActionEvent e) {
        if (boardPanel1.getMyTurn()) {
            saveGame();
        } else {
            JOptionPane.showMessageDialog(null, "You can only save on Player 1's turn");
        }
    }

    //EFFECTS: saves game to file
    private static void saveGame() {
        try {
            jsonWrite1.open();
            jsonWrite1.write(game.getBoard1());
            jsonWrite2.open();
            jsonWrite2.write(game.getBoard2());
            jsonWrite1.close();
            jsonWrite2.close();
            JOptionPane.showMessageDialog(null, "The game has been saved. You can safely close"
                    + "the app.");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save game");
        }
    }

    //EFFECTS: loads the game
    private static void loadGame() {
        try {
            game.setBoard1(jsonRead1.read());
            game.setBoard2(jsonRead2.read());
//            game1.getBoard1().setBoardArray(jsonRead1.read().getBoardArray());
//            game1.getBoard2().setBoardArray(jsonRead2.read().getBoardArray());
        } catch (IOException e) {
            System.out.println("Unable to load");
        }
    }

    //EFFECTS: displays welcome message + creates a new game and prompts user inputs or loads game
    private void setup() {
        String input;
        userInput = new UserInput();

        JOptionPane.showMessageDialog(null, "Welcome to Battleship! Please only input integers "
                + "like '1' and '3' anywhere where input is required. The size of the board is 8x8, and the square are"
                + " accessed by using zero-based indexing. Therefore, all inputs should be between 0-7.");

        while (true) {
            input = JOptionPane.showInputDialog("Would you like to like to load game (type 'l')"
                    + "or start a new game (click 'ok')");
            if (input.equals("l")) {
                loadGame(); // not loading game
            } else {
                takeUserInput(game, userInput, game.getBoard1());
                takeUserInput(game, userInput, game.getBoard2());
            }
            break;
        }
    }


    //MODIFIES: board
    //EFFECTS: prompts the user for their desired ship and adds the ship if its valid
    public static void takeUserInput(Game g, UserInput u, Board b) { // does this need a game param?
        int shipCount = u.userInputShipCount();
        int counter = 0;
        while (counter < shipCount) {

            int size = u.userInputSize();

            int dir = u.userInputDir();

            int x = u.setupCoordX();

            int y = u.setupCoordY();

            if (b.addShip(size, x, y, dir)) {
                counter++;
                JOptionPane.showMessageDialog(null, "Ship was added successfully");
            } else {
                JOptionPane.showMessageDialog(null,"Invalid ship, try again");
            }
        }
    }
}
