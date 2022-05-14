package main.DungeonGUI;

import main.DungeonCharacter.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class DungeonUI extends JPanel{
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

    final private String[] heroes = {"None","Warrior", "Priestess", "Thief"};
    private String name = "";
    private static DungeonCharacter player;
    private String userClass = "";

    public void DungeonUI(DungeonGame.ChoiceController handleChoice) throws IOException {

        try {
            gameTitleFont = Font.createFont(Font.TRUETYPE_FONT, new File("Antique Quest St.ttf")).deriveFont(40f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Antique Quest St.ttf")));
        }
        catch (IOException | FontFormatException ignored) {
        }

        window = new JFrame();
        window.setSize(800, 600);

        //puts frame in middle of screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width / 2 - window.getSize().width / 2, dim.height / 2 - window.getSize().height / 2);

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


        nameInputPanel.add(nameInputLabel);
        nameInputPanel.add(nameInputBox);
        nameInputPanel.add(nameSubmitButton);
        container.add(nameInputPanel);
        nameInputPanel.setVisible(false);

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
        choices.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userClass = (String) choices.getSelectedItem();
                switch(Objects.requireNonNull(userClass)) {
                    case "Warrior" -> player = new Warrior(name);
                    case "Priestess" -> player = new Priestess(name);
                    case "Thief" -> player = new Thief(name);
                }
            }
        });
        heroSelectPanel.add(choices);
        heroSelectButton = new JButton("OK");
        heroSelectButton.setBackground(Color.BLACK);
        heroSelectButton.setForeground(Color.WHITE);
        heroSelectButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        heroSelectButton.addActionListener(handleChoice);
        heroSelectButton.setActionCommand("hero");
        heroSelectButton.addActionListener(e -> playerIcon = new JLabel(new ImageIcon()));

        heroSelectPanel.add(heroSelectButton);
        container.add(heroSelectPanel);
        heroSelectPanel.setVisible(false);


        //Dungeon Rooms
        dungeonRoomPanel = new JPanel();
        dungeonRoomPanel.setBounds(0,0,800,600);
        dungeonRoomPanel.setBackground(Color.WHITE);

//        testButton = new JButton("Test");
//        testButton.setBackground(Color.BLACK);
//        testButton.setForeground(Color.WHITE);
//        testButton.setFont(regularFont);
//        testButton.setFocusPainted(false);
//        testButton.addActionListener(e -> System.out.println(player + " " + player.getMyName()));
//        BufferedImage topLeftWall = ImageIO.read(new File("src/TopLeftWall.png"));
//        BufferedImage topRightWall = ImageIO.read(new File("src/TopRightWall.png"));
//        BufferedImage bottomLeftWall = ImageIO.read(new File("src/BottomLeftWall.png"));
//        BufferedImage bottomRightWall = ImageIO.read(new File("src/BottomRightWall.png"));
//        ImageIcon topLeftWallImage = new ImageIcon("src/img.png");
//        topLeftWallLabel = new JLabel(topLeftWallImage);
//        topLeftWallLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
//        topRightWallLabel = new JLabel(new ImageIcon("src/TopRightWall.png"));
//        bottomLeftWallLabel = new JLabel(new ImageIcon("src/BottomLeftWall.png"));
//        bottomRightWallLabel = new JLabel(new ImageIcon("src/BottomRightWall.png"));

//        dungeonRoomPanel.setVisible(false);
        dungeonRoomPanel.add(testButton);
        container.add(dungeonRoomPanel);
        window.setVisible(true);
    }
}