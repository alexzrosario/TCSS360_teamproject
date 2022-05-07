package main.DungeonGUI;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Handler;

public class DungeonUI {
    JFrame window;
    Container container;

    JPanel gameTitlePanel;
    JPanel gameStartPanel;
    JPanel heroSelectPanel;

    JLabel gameTitleLabel;
    JLabel heroSelectLabel;

    JButton gameStartButton;
    JButton heroSelectButton;

    Font gameTitleFont;
    Font regularFont = new Font("Times New Roman", Font.PLAIN, 20);

    final String[] heroes = {"Warrior", "Priestess", "Thief"};

    public void DungeonUI(DungeonGame.Choices handleChoice) {

        try {
            gameTitleFont = Font.createFont(Font.TRUETYPE_FONT, new File("Antique Quest St.ttf")).deriveFont(40f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Antique Quest St.ttf")));
        }
        catch (IOException | FontFormatException ignored) {
        }

        window = new JFrame();
        window.setSize(800, 600);
        window.getContentPane().setBackground(Color.WHITE);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);
        container = window.getContentPane();

        //Title
        gameTitlePanel = new JPanel();
        gameTitlePanel.setBounds(150, 25, 500, 150);
        gameTitlePanel.setBackground(Color.BLACK);
        gameTitleLabel = new JLabel("Dungeon Adventure");
        gameTitleLabel.setForeground(Color.WHITE);
        gameTitleLabel.setFont(gameTitleFont);
        gameTitlePanel.add(gameTitleLabel);
        container.add(gameTitlePanel);

        //Start Button
        gameStartPanel = new JPanel();
        gameStartPanel.setBounds(300, 400, 200, 100);
        gameStartPanel.setBackground(Color.BLACK);
        gameStartButton = new JButton("START");
        gameStartButton.setBackground(Color.BLACK);
        gameStartButton.setForeground(Color.WHITE);
        gameStartButton.setFont(regularFont);
        gameStartButton.setFocusPainted(false);
        gameStartButton.addActionListener(handleChoice);
        gameStartButton.setActionCommand("start");
        gameStartPanel.add(gameStartButton);
        container.add(gameStartPanel);

        //Hero Selection
        heroSelectPanel = new JPanel();
        heroSelectPanel.setLayout(new BoxLayout(heroSelectPanel, BoxLayout.Y_AXIS));
        heroSelectPanel.setBounds(150, 25, 500, 150);
        heroSelectPanel.setBackground(Color.BLACK);
        heroSelectLabel = new JLabel("Select your hero!");
        heroSelectLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        heroSelectPanel.add(heroSelectLabel);
        final JComboBox<String> choices = new JComboBox<>(heroes);
        choices.setMaximumSize(choices.getPreferredSize());
        choices.setAlignmentX(Component.CENTER_ALIGNMENT);
        heroSelectPanel.add(choices);
        heroSelectButton = new JButton("OK");
        heroSelectButton.setBackground(Color.BLACK);
        heroSelectButton.setForeground(Color.WHITE);
        heroSelectButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        heroSelectPanel.add(heroSelectButton);
        container.add(heroSelectPanel);
        heroSelectPanel.setVisible(false);




        window.setVisible(true);
    }
}