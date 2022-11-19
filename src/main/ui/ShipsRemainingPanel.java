package ui;

import model.Board;
import model.Game;

import javax.swing.*;
import java.awt.*;

public class ShipsRemainingPanel extends JPanel {

    private static final String SHIPS_REMAIN_TXT = "Ships to find: ";
    private JLabel shipsRemainLbl1;
    Board board;

    public ShipsRemainingPanel(Board b) {
        board = b;

        setBackground(new Color(180, 180,180));
        shipsRemainLbl1 = new JLabel(SHIPS_REMAIN_TXT + b.getShipsRemaining());
        shipsRemainLbl1.setPreferredSize(new Dimension(200, 30));
        add(shipsRemainLbl1);
    }

    public void updateShipsRemainingPanel() {
        shipsRemainLbl1.setText(SHIPS_REMAIN_TXT + board.getShipsRemaining());
    }
}
