package ui;

import model.Board;

import javax.swing.*;
import java.awt.*;

// Represents the panel that tracks the number of ships remaining
public class ShipsRemainingPanel extends JPanel {

    private static final String SHIPS_REMAIN_TXT = "Ships to find: ";
    private JLabel shipsRemainLbl1;
    Board board;

    //EFFECTS: Creates a shipsRemainingPanel
    public ShipsRemainingPanel(Board b) {
        board = b;

        setBackground(new Color(180, 180,180));
        shipsRemainLbl1 = new JLabel(SHIPS_REMAIN_TXT + b.getShipsRemaining());
        shipsRemainLbl1.setPreferredSize(new Dimension(200, 30));
        add(shipsRemainLbl1);
    }

    //EFFECTS: Updates the text in the shipsRemainingPanel
    public void updateShipsRemainingPanel() {
        shipsRemainLbl1.setText(SHIPS_REMAIN_TXT + board.getShipsRemaining());
    }
}
