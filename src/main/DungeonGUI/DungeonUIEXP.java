package main.DungeonGUI;

import main.Controller;
import main.ControllerEXP;
import main.DungeonCharacter.Hero;
import main.DungeonMain.Dungeon;
import main.DungeonMain.Room;

import javax.sound.sampled.Control;
import javax.swing.*;
import java.awt.*;
import java.awt.desktop.AboutEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DungeonUIEXP extends JFrame {
    Hero myHero;
    private ControllerEXP myController = new ControllerEXP(this);

    JPanel myMainPanel;

    JPanel myStartPanel;
    JPanel myOptionsPanel;
    JPanel myClassSelectPanel;
    final private String[] myHeroes = {"Warrior", "Priestess", "Thief"};
    final private Integer[] mySizes = {3, 4, 5, 6, 7, 8, 9, 10};
    String myUserHero;
    final private String[] myDifficulty = {"EASY", "NORMAL", "HARD"};

    JPanel myDungeonSizePanel;
    int myDungeonSize;

    JPanel myDifficultySelectPanel;
    String myUserDifficulty;

    JPanel myAdventurePanel;
    JPanel myDungeonPanel;
    JPanel myControlPanel;
    JPanel myNavigationPanel;
    JPanel myInteractionsPanel;
    JTextField myAdventureText;
    JTextField myBlankTextFieldWhite = new JTextField();
    JTextField myBlankTextFieldBlack = new JTextField();

    JButton testButton;
    Font gameTitleFont;
    Font regularFont = new Font("Times New Roman", Font.PLAIN, 20);

    public DungeonUIEXP() {
        this.setTitle("Dungeon Adventure");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 1000);
        myMainPanel = new JPanel();
        this.add(myMainPanel);
    }

    public void start() {
        buildStartPanel();
    }

    public void buildAdventurePanel(Room theCurrentRoom, Hero theHero) {
        myAdventurePanel = new JPanel();
        myAdventurePanel.setBackground(Color.WHITE);
        myAdventurePanel.setLayout(new BoxLayout(myAdventurePanel, BoxLayout.Y_AXIS));
        //myMainPanel.setLayout(new GridLayout(0, 1));
        setBlankTextFields();

        myDungeonPanel = dungeonPanel(theCurrentRoom, theHero);
        myAdventurePanel.add(myDungeonPanel);

        myControlPanel = new JPanel();
        myControlPanel.setLayout(new GridLayout(1, 3));
        myAdventureText = new JTextField();
        myAdventureText.setEditable(false);
        myControlPanel.add(myAdventureText);

        buildNavigationPanel();
        myControlPanel.add(myNavigationPanel);

        buildInteractionsPanel();
        myControlPanel.add(myInteractionsPanel);
        myAdventurePanel.add(myControlPanel);

        this.add(myAdventurePanel);
        this.setVisible(true);
    }

    public JPanel dungeonPanel(Room theRoom, Hero theHero){
        JPanel dungeonPanel = new JPanel();
        dungeonPanel.setLayout(new GridLayout(0, 3));
        JTextField temp;
        for(int i = 0; i < 9; i++) {
            temp = new JTextField();
            temp.setEditable(false);
            if (i == 0 || i == 2 || i == 6 || i == 8) {
                temp.setBackground(Color.DARK_GRAY);
            }
            else if (i == 1) {
                if (theRoom.getMyNorthRoom() == null) temp.setBackground(Color.DARK_GRAY);
                else if (theRoom.getMyNorthRoom() != null && theRoom.getMyNorthRoom().isSeen()) {
                    temp.setText(theRoom.getMyNorthRoom().getMyStringToken());
                }
            }
            else if (i == 3) {
                if (theRoom.getMyWestRoom() == null) temp.setBackground(Color.DARK_GRAY);
                else if (theRoom.getMyWestRoom() != null && theRoom.getMyWestRoom().isSeen()) {
                    temp.setText(theRoom.getMyWestRoom().getMyStringToken());
                }
            }
            else if (i ==4) {
                JLabel playerIcon;
                playerIcon = new JLabel(new ImageIcon(new ImageIcon("src/" + theHero.getMyImage()).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
                dungeonPanel.add(playerIcon);
                continue;
            }
            else if (i == 5) {
                if (theRoom.getMyEastRoom() == null) temp.setBackground(Color.DARK_GRAY);
                else if (theRoom.getMyEastRoom() != null && theRoom.getMyEastRoom().isSeen()) {
                    temp.setText(theRoom.getMyEastRoom().getMyStringToken());
                }
            }
            else {
                if (theRoom.getMySouthRoom() == null) temp.setBackground(Color.DARK_GRAY);
                else if (theRoom.getMySouthRoom() != null && theRoom.getMySouthRoom().isSeen()) {
                    temp.setText(theRoom.getMySouthRoom().getMyStringToken());
                }
            }
            dungeonPanel.add(temp);
        }
        return dungeonPanel;
    }

    public void setBlankTextFields() {
        myBlankTextFieldWhite.setEditable(false);
        myBlankTextFieldBlack.setEditable(false);
        myBlankTextFieldBlack.setBackground(Color.BLACK);
    }

    public void buildNavigationPanel() {
        JButton upButton = new JButton("^");
        JButton leftButton = new JButton("<");
        JButton rightButton = new JButton(">");
        JButton downButton = new JButton("v");

        myNavigationPanel = new JPanel();
        myNavigationPanel.setLayout(new GridLayout(0, 3));
        myNavigationPanel.add(new JLabel());
        myNavigationPanel.add(upButton);
        myNavigationPanel.add(new JLabel());
        myNavigationPanel.add(leftButton);
        myNavigationPanel.add(new JLabel());
        myNavigationPanel.add(rightButton);
        myNavigationPanel.add(new JLabel());
        myNavigationPanel.add(downButton);
        myNavigationPanel.add(new JLabel());
    }

    public void buildInteractionsPanel() {
        JButton heroInfobutton = new JButton("Hero Info");
        JButton healPotionButton = new JButton("Use Heal Potion");
        JButton visionPotionButton = new JButton("Use Vision Potion");
        JButton saveGameButton = new JButton("Save Game");
        JButton quitGameButton = new JButton("Quit Game");

        myInteractionsPanel = new JPanel();
        myInteractionsPanel.setLayout(new BoxLayout(myInteractionsPanel, BoxLayout.Y_AXIS));
        myInteractionsPanel.add(heroInfobutton);
        myInteractionsPanel.add(healPotionButton);
        myInteractionsPanel.add(visionPotionButton);
        myInteractionsPanel.add(saveGameButton);
        myInteractionsPanel.add(quitGameButton);
        myInteractionsPanel.setBorder(BorderFactory.createTitledBorder("Options"));

    }

    public void buildStartPanel() {
        JButton startButton = new JButton("START NEW GAME");
        JButton loadButton = new JButton("LOAD GAME");
        JButton quitButton = new JButton("QUIT GAME");
        myStartPanel = new JPanel();
        myStartPanel.add(startButton);
        myStartPanel.add(loadButton);
        myStartPanel.add(quitButton);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myMainPanel.removeAll();
                myMainPanel.repaint();
                buildOptionsPanel();
            }
        });
        myMainPanel.add(myStartPanel);
        this.setVisible(true);
    }

    public void buildOptionsPanel() {
        myOptionsPanel = new JPanel();
        myOptionsPanel.setLayout(new GridLayout(4,1));

        JComboBox<String> heroSelectBox = new JComboBox<>(myHeroes);
        heroSelectBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myUserHero = (String)heroSelectBox.getSelectedItem();
            }
        });
        myOptionsPanel.add(heroSelectBox);

        JComboBox<Integer> dungeonSize = new JComboBox<>(mySizes);
        dungeonSize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myDungeonSize = (int) dungeonSize.getSelectedItem();
            }
        });
        myOptionsPanel.add(dungeonSize);

        JComboBox<String> difficultySelectBox = new JComboBox<>(myDifficulty);
        difficultySelectBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myUserDifficulty = (String)difficultySelectBox.getSelectedItem();
            }
        });
        myOptionsPanel.add(difficultySelectBox);

        JButton startAdventureButton = new JButton("Start Adventure");
        startAdventureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        myOptionsPanel.add(startAdventureButton);

        myMainPanel.add(myOptionsPanel);
        this.setVisible(true);
    }
}
