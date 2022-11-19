package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Board;
import model.Game;

// Represents an individual board + its ships remaining counter
public class BoardPanel extends JPanel implements ActionListener {
    private JPanel board;
    private JButton[] buttonArray;
    private int player;
    private Game game;
    private BoardPanel enemyBoard;
    private ShipsRemainingPanel shipsRemainingPanel;
    private boolean myTurn;

//EFFECTS: Creates a new boardpanel object
    public BoardPanel(int p, Game g, ShipsRemainingPanel shipsRemaining) {
        game = g;
        player = p;
        board = new JPanel(new GridLayout(8, 8, 0, 0));
        buttonArray = new JButton[64];
        shipsRemainingPanel = shipsRemaining;
        myTurn = false;


        for (int i = 0; i < 64; i++) {
            buttonArray[i] = new JButton("" + i);
            buttonArray[i].setForeground(Color.BLACK);
            buttonArray[i].setBackground(Color.WHITE);

            buttonArray[i].setPreferredSize(new Dimension(100,100));

            java.net.URL imgURL = getClass().getResource("resources/Water.jpg");
            buttonArray[i].setIcon(new ImageIcon(imgURL));

            buttonArray[i].addActionListener(this);
            board.add(buttonArray[i]);
        }

        if (player == 1) {
            updateBoard(game.getBoard2());
        } else if (player == 2) {
            updateBoard(game.getBoard1());
        }

        board.setVisible(true);
    }

    //EFFECTS: returns JPanel board
    public JPanel getBoard() {
        return board;
    }

    //EFFECTS: sets myTurn boolean to the param
    public void setMyTurn(boolean b) {
        myTurn = b;
    }

    //EFFECTS: returns myTurn bool
    public boolean getMyTurn() {
        return myTurn;
    }

    //EFFECTS: sets enemyBoard to the param
    public void setEnemyBoardPanel(BoardPanel enemy) { // not using this
        enemyBoard = enemy;
    }

    //MODIFIES: this
    //EFFECTS: enables all buttons on chosen board; sets myTurn for self to false and enemy to true
    public void enableCorrect(Board b) {
        int counter = 0;

        enemyBoard.setMyTurn(true);
        setMyTurn(false);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                enemyBoard.getButtonArray()[counter].setEnabled(true);
                counter++;
            }
        }
        updateBoard(b);
    }

    //EFFECTS: updates the gui to display the correct image for the state of each coordinate
    private void updateBoard(Board b) {
        int counter = 0;
        int [][] boardArray = b.getBoardArray();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (boardArray[i][j] == 2) {
                    buttonArray[counter].setText("-1");
                    java.net.URL imgURL = getClass().getResource("resources/Splash.jpg");
                    buttonArray[counter].setIcon(new ImageIcon(imgURL));
                } else if (boardArray[i][j] == 3) {
                    buttonArray[counter].setText("-1");
                    java.net.URL imgURL = getClass().getResource("resources/explosion.jpg");
                    buttonArray[counter].setIcon(new ImageIcon(imgURL));
                }
                counter++;
            }
        }
    }

    //EFFECTS: returns the array of buttons
    public JButton[] getButtonArray() {
        return buttonArray;
    }

    //EFFECTS: disables all buttons on this board
    public void disableAll() {
        for (int i = 0; i < 64; i++) {
            buttonArray[i].setEnabled(false);
        }
    }

    //EFFECTS: determines correct player and runs their turn
    @Override
    public void actionPerformed(ActionEvent e) { // this will be called everytime a button is clicked
        JButton clicked = (JButton) e.getSource(); // get the button that was clicked

        int button = Integer.parseInt(clicked.getText());

        if (button != -1) {
            int x = button % 8;
            int y = button / 8;

            clicked.setText("-1");
            if (player == 1) {
                if (game.getBoard2().turn(x,y)) {
                    // alert game over Player 2 wins
                    JOptionPane.showMessageDialog(null, "Player 1 wins!");
                    // use WindowEvent.WINDOW_CLOSED;
                } else {
                    enableCorrect(game.getBoard2());
                }
                shipsRemainingPanel.updateShipsRemainingPanel();
            } else {
                if (game.getBoard1().turn(x,y)) {
                    // alert game over Player 1 wins
                    JOptionPane.showMessageDialog(null, "Player 2 wins!");
                } else {
                    enableCorrect(game.getBoard1());
                }
                shipsRemainingPanel.updateShipsRemainingPanel();
            }
            disableAll();
        }
    }
}
