package main.DungeonGUI;

import main.Controller;
import main.DungeonCharacter.*;
import main.DungeonMain.Dungeon;
import main.DungeonMain.DungeonAdventure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private Dungeon myDungeon = new Dungeon(5, 5);
    final private String[] heroes = {"None","Warrior", "Priestess", "Thief"};
    private String name = "";
    private String userClass = "";
    DungeonAdventure dummy = new DungeonAdventure();
    private Controller controller = new Controller(dummy);
    ChoiceController handleChoice = new ChoiceController();
    private DungeonUIManager um = new DungeonUIManager(this);
    public void DungeonUI() throws IOException {

        try {
            gameTitleFont = Font.createFont(Font.TRUETYPE_FONT, new File("Antique Quest St.ttf")).deriveFont(40f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Antique Quest St.ttf")));
        }
        catch (IOException | FontFormatException ignored) {
        }

        window = new JFrame();
        window.setSize(1200, 1000);

        //puts frame in middle of screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width / 2 - window.getSize().width / 2, dim.height / 2 - window.getSize().height / 2);

        window.getContentPane().setBackground(Color.WHITE);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new GridBagLayout());
        window.setResizable(false);
        container = window.getContentPane();

        gameTitle();
        namePanel();
        heroSelect();
        dungeonRooms();

        window.setVisible(true);

        //Dungeon
    }
    public void gameTitle() {
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
    }

    public void namePanel() {
        //Name Input
        nameInputPanel = new JPanel();
        nameInputPanel.setBounds(150, 25, 500, 300);
        nameInputPanel.setBackground(Color.BLACK);
        nameInputLabel = new JLabel("Enter your Hero's name:");
        nameInputLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameInputBox = new JTextField(10);
        nameSubmitButton = new JButton();
        nameSubmitButton.setBackground(Color.BLACK);
        nameSubmitButton.setForeground(Color.WHITE);
        nameSubmitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameSubmitButton.addActionListener(e -> name = nameInputBox.getText());
        nameSubmitButton.addActionListener(handleChoice);
        nameSubmitButton.setActionCommand("name");

        nameInputPanel.add(nameInputLabel);
        nameInputPanel.add(nameInputBox);
        nameInputPanel.add(nameSubmitButton);
        container.add(nameInputPanel);
        nameInputPanel.setVisible(false);
    }

    public void heroSelect() {
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
                switch (Objects.requireNonNull(userClass)) {
                    case "Warrior" -> {
                        controller.startGame(name, 1);
                        playerIcon = new JLabel(new ImageIcon(new ImageIcon("src/WarriorImage.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
                        dungeonRoomPanel.add(playerIcon);
                    }
                    case "Priestess" -> {
                        controller.startGame(name, 2);
                        playerIcon = new JLabel(new ImageIcon(new ImageIcon("src/PriestessImage.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
                        dungeonRoomPanel.add(playerIcon);
                    }
                    case "Thief" -> {
                        controller.startGame(name, 3);
                        playerIcon = new JLabel(new ImageIcon(new ImageIcon("src/ThiefImage.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
                        dungeonRoomPanel.add(playerIcon);
                    }
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

        heroSelectPanel.add(heroSelectButton);
        container.add(heroSelectPanel);
        heroSelectPanel.setVisible(false);
    }

    public void dungeonRooms() {
        dungeonRoomPanel = new JPanel();
        dungeonRoomPanel.setBounds(0,0,1200,1000);
        dungeonRoomPanel.setBackground(Color.WHITE);
        dungeonRoomPanel.setLayout(new GridLayout(myDungeon.getMyDungeonRows(), myDungeon.getMyDungeonCols()));
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
//        topLeftWallLabel.setBounds(0, 0, 50, 50);
//        topRightWallLabel = new JLabel(new ImageIcon("src/TopRightWall.png"));
//        bottomLeftWallLabel = new JLabel(new ImageIcon("src/BottomLeftWall.png"));
//        bottomRightWallLabel = new JLabel(new ImageIcon("src/BottomRightWall.png"));

        container.add(dungeonRoomPanel);
        dungeonRoomPanel.setVisible(false);
    }

    public class ChoiceController implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String choice = event.getActionCommand();
            um.titleScreen();
            switch (choice) {
                case "start" -> um.nameInputScreen();
                case "name" -> um.heroSelectScreen();
                case "hero" -> um.dungeonRoomScreen();
            }
        }
    }

}