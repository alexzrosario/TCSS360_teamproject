package main.DungeonGUI;

import main.ControllerEXP;
import main.DungeonCharacter.Hero;
import main.DungeonCharacter.Monster;
import main.DungeonCharacter.Warrior;
import main.DungeonMain.Room;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DungeonUIEXP extends JFrame {
    private ControllerEXP myController = new ControllerEXP(this);

    JPanel myMainPanel;

    JLabel myHeroIcon;

    JPanel myStartPanel;
    JPanel myOptionsPanel;
    JPanel myClassSelectPanel;
    final private String[] myHeroes = {"Warrior", "Priestess", "Thief", "Barbarian", "Mage", "Swordsman", "Monk", "Samurai", "Occultist"};
    final private Integer[] mySizes = {3, 4, 5, 6, 7, 8, 9, 10};
    String myUserHero = "Warrior";
    final private String[] myDifficulty = {"EASY", "NORMAL", "HARD"};

    JPanel myDungeonSizePanel;
    int myDungeonSize = 5;

    JPanel myDifficultySelectPanel;
    String myUserDifficulty = "NORMAL";

    JPanel myAdventurePanel;
    JPanel myDungeonPanel;
    JPanel myControlPanel;
    JPanel myNavigationPanel;
    JPanel myInteractionsPanel;
    JTextArea myAdventureTextBox = new JTextArea("");

    JPanel myBattlePanel;

    JButton testButton;
    Font gameTitleFont;
    Font regularFont = new Font("Times New Roman", Font.PLAIN, 20);

    public DungeonUIEXP() {
        this.setTitle("Dungeon Adventure");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 1000);
        myMainPanel = new JPanel();
        myMainPanel.setLayout(new GridLayout(0,1));
        myAdventureTextBox.setPreferredSize(new Dimension(20,0));
        this.add(myMainPanel);
    }

    public void start() {
        buildStartPanel();
    }

    public void buildAdventurePanel(Room theCurrentRoom) {
        myMainPanel.removeAll();
        myMainPanel.repaint();
        myAdventurePanel = new JPanel();
        myAdventurePanel.setBackground(Color.WHITE);
        myAdventurePanel.setLayout(new BoxLayout(myAdventurePanel, BoxLayout.Y_AXIS));
        //myMainPanel.setLayout(new GridLayout(0, 1));

        myDungeonPanel = dungeonPanel(theCurrentRoom);
        myAdventurePanel.add(myDungeonPanel);

        myControlPanel = new JPanel();
        myControlPanel.setLayout(new GridLayout(1, 3));
        myControlPanel.add(myAdventureTextBox);

        buildNavigationPanel();
        myControlPanel.add(myNavigationPanel);

        buildInteractionsPanel();
        myControlPanel.add(myInteractionsPanel);
        myAdventurePanel.add(myControlPanel);

        myMainPanel.add(myAdventurePanel);
        this.setVisible(true);
    }

    public JPanel dungeonPanel(Room theRoom){
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
                playerIcon = new JLabel(new ImageIcon(new ImageIcon("src/" + myUserHero + "Image.png").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
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

    public void buildNavigationPanel() {
        JButton upButton = new JButton("^");
        if (myController.getMyCurrRoom().getMyNorthRoom() == null) upButton.setEnabled(false);
        JButton leftButton = new JButton("<");
        if (myController.getMyCurrRoom().getMyWestRoom() == null) leftButton.setEnabled(false);
        JButton rightButton = new JButton(">");
        if (myController.getMyCurrRoom().getMyEastRoom() == null) rightButton.setEnabled(false);
        JButton downButton = new JButton("v");
        if (myController.getMyCurrRoom().getMySouthRoom() == null) downButton.setEnabled(false);

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

        upButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myController.checkDirection("^");
                buildAdventurePanel(myController.getMyCurrRoom());
            }
        });

        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myController.checkDirection("<");
                buildAdventurePanel(myController.getMyCurrRoom());
            }
        });

        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myController.checkDirection(">");
                buildAdventurePanel(myController.getMyCurrRoom());
            }
        });

        downButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myController.checkDirection("v");
                buildAdventurePanel(myController.getMyCurrRoom());
            }
        });

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
        myMainPanel.removeAll();
        myMainPanel.repaint();
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
                buildOptionsPanel();
            }
        });
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        myMainPanel.add(myStartPanel);
        this.setVisible(true);
    }

    public void buildOptionsPanel() {
        myMainPanel.removeAll();
        myMainPanel.repaint();
        myOptionsPanel = new JPanel();
        myOptionsPanel.setLayout(new GridLayout(4,1));

        JTextField nameField = new JTextField();
        nameField.setText("");
        myOptionsPanel.add(nameField);

        JComboBox<String> heroSelectBox = new JComboBox<>(myHeroes);
        heroSelectBox.setSelectedItem(myHeroes[0]);
        heroSelectBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myUserHero = (String)heroSelectBox.getSelectedItem();
            }
        });
        myOptionsPanel.add(heroSelectBox);

        JComboBox<Integer> dungeonSize = new JComboBox<>(mySizes);
        dungeonSize.setSelectedItem(mySizes[2]);
        dungeonSize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myDungeonSize = (int) dungeonSize.getSelectedItem();
            }
        });
        myOptionsPanel.add(dungeonSize);

        JComboBox<String> difficultySelectBox = new JComboBox<>(myDifficulty);
        difficultySelectBox.setSelectedItem(myDifficulty[1]);
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
                String heroName = nameField.getSelectedText();
                myController.startGame(heroName, myUserHero, myUserDifficulty, myDungeonSize);
                buildAdventurePanel(myController.getMyCurrRoom());
            }
        });
        myOptionsPanel.add(startAdventureButton);

        myMainPanel.add(myOptionsPanel);
        this.setVisible(true);
    }

    public void buildBattlePanel(Hero theHero, Monster theMonster) {
        myMainPanel.removeAll();
        myMainPanel.repaint();
        myBattlePanel = new JPanel();
        myBattlePanel.setLayout(new GridLayout(0,3));

        JPanel heroBattlePanel = new JPanel();
        heroBattlePanel.setLayout(new GridLayout(3,1));
        heroBattlePanel.add(new JLabel(new ImageIcon(new ImageIcon("src/" + myUserHero + "Image.png").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH))));
        heroBattlePanel.add(new JTextArea(theHero.getMyName() + "'s HP: " + theHero.getMyHitPoints()));

        JPanel heroBattleOptions = new JPanel();
        JButton attackButton = new JButton("ATTACK");
        heroBattleOptions.add(attackButton);
        JButton skillButton = new JButton(theHero.getMySkillName());
        heroBattleOptions.add(skillButton);
        JButton useHealButton = new JButton("USE HEAL POTION: " + theHero.getMyHealingPotions());
        heroBattleOptions.add(useHealButton);
        heroBattlePanel.add(heroBattleOptions);
        myBattlePanel.add(heroBattlePanel);

        myBattlePanel.add(myAdventureTextBox);

        JPanel monsterBattlePanel = new JPanel();
        monsterBattlePanel.setLayout(new GridLayout(2,1));
        monsterBattlePanel.add(new JLabel(new ImageIcon(new ImageIcon("src/" + theMonster.getMyName() + "Image.png").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH))));
        monsterBattlePanel.add(new JTextArea(theMonster.getMyName() + "'s HP: " + theMonster.getMyHitPoints()));
        myBattlePanel.add(monsterBattlePanel);

        myMainPanel.add(myBattlePanel);
    }

    public void updateAdventureText(String newText) {
        try {
            myAdventureTextBox.getDocument().insertString(0, newText + "\n", null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
}
