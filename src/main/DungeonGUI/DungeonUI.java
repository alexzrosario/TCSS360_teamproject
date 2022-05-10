package main.DungeonGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class DungeonUI {
    JFrame window;
    Container container;

    JPanel gameTitlePanel;
    JPanel gameStartPanel;
    JPanel heroSelectPanel;
    JPanel nameInputPanel;

    JLabel gameTitleLabel;
    JLabel heroSelectLabel;

    JButton gameStartButton;
    JButton heroSelectButton;

    JTextField nameInputBox;
    JButton nameSubmitButton;
    JLabel nameInputLabel;
    JComboBox<String> choices;

    Font gameTitleFont;
    Font regularFont = new Font("Times New Roman", Font.PLAIN, 20);

    final String[] heroes = {"Warrior", "Priestess", "Thief"};
    String name = "";

    public void DungeonUI(DungeonGame.ChoiceController handleChoice) {

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
        heroSelectPanel.setBounds(150, 25, 500, 300);
        heroSelectPanel.setBackground(Color.BLACK);
        heroSelectLabel = new JLabel("Select your hero!");
        heroSelectLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        heroSelectPanel.add(heroSelectLabel);
        choices = new JComboBox<>(heroes);
        choices.setMaximumSize(choices.getPreferredSize());
        choices.setAlignmentX(Component.CENTER_ALIGNMENT);
        choices.addActionListener(handleChoice);

        heroSelectPanel.add(choices);
        heroSelectButton = new JButton("OK");
        heroSelectButton.setBackground(Color.BLACK);
        heroSelectButton.setForeground(Color.WHITE);
        heroSelectButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        heroSelectButton.addActionListener(handleChoice);
        heroSelectButton.setActionCommand("hero");
        heroSelectPanel.add(heroSelectButton);
        container.add(heroSelectPanel);
        heroSelectPanel.setVisible(false);

        //Name Input
        nameInputPanel = new JPanel();
        nameInputPanel.setBounds(150, 25, 500, 300);
        nameInputPanel.setBackground(Color.BLACK);
        nameInputLabel = new JLabel("Enter your Hero's name:");
        nameInputLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameInputBox = new JTextField(10);
        nameInputBox.addActionListener(e -> name = nameInputBox.getText());
        nameSubmitButton = new JButton();
        nameSubmitButton.setBackground(Color.BLACK);
        nameSubmitButton.setForeground(Color.WHITE);
        nameSubmitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameSubmitButton.addActionListener(handleChoice);
        nameSubmitButton.setActionCommand("name");

        //nameSubmitButton.addActionListener(e -> System.out.println(name));

        nameInputPanel.add(nameInputLabel);
        nameInputPanel.add(nameInputBox);
        nameInputPanel.add(nameSubmitButton);
        container.add(nameInputPanel);
        nameInputPanel.setVisible(false);

        window.setVisible(true);
    }
}