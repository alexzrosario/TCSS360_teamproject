package main.DungeonGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.desktop.AboutEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DungeonUIEXP extends JFrame {
    JFrame window;
    Container container;

    JPanel gameTitlePanel;
    JPanel gameStartPanel;
    JPanel heroSelectPanel;
    JPanel nameInputPanel;
    JPanel dungeonRoomPanel;

    JLabel gameTitleLabel;
    JLabel heroSelectLabel;

    JButton gameStartButton;
    JButton heroSelectButton;

    JTextField nameInputBox;
    JButton nameSubmitButton;
    JLabel nameInputLabel;
    JComboBox<String> choices;

    JLabel topLeftWallLabel;
    JLabel topRightWallLabel;
    JLabel bottomLeftWallLabel;
    JLabel bottomRightWallLabel;

    JLabel playerIcon;

    JButton testButton;
    Font gameTitleFont;
    Font regularFont = new Font("Times New Roman", Font.PLAIN, 20);

    public DungeonUIEXP() {
        this.setTitle("Dungeon Adventure");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 1000);
        JPanel mainPanel = new JPanel();
    }
}
