package main.DungeonGUI;

import main.AudioController;
import main.Controller;
import main.ControllerEXP;
import main.DungeonCharacter.Hero;
import main.DungeonCharacter.Monster;
import main.DungeonMain.Room;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class DungeonUIEXP extends JFrame implements Serializable {
    private ControllerEXP myController = new ControllerEXP(this);
    private AudioController audioController = new AudioController();
    private static final long serialVersionUID = 3536060713340084481L;

    JPanel myMainPanel;


    JPanel myStartPanel;
    JPanel myOptionsPanel;
    JPanel myClassSelectPanel;
    final private String[] myHeroes = {"Warrior", "Priestess", "Thief", "Barbarian", "Mage", "Swordsman", "Monk", "Samurai", "Occultist"};
    final private Integer[] mySizes = {3, 4, 5, 6, 7, 8, 9, 10};
    String myUserHero = "Warrior";
    final private String[] myDifficulty = {"EASY", "NORMAL", "HARD"};

    int myDungeonSize = 5;

    String myUserDifficulty = "NORMAL";

    JPanel myAdventurePanel;
    JPanel myDungeonPanel;
    JPanel myControlPanel;
    JPanel myNavigationPanel;
    JPanel myInteractionsPanel;
    JTextArea myAdventureTextBox = new JTextArea("");

    JPanel myBattlePanel;

    JButton returnButton;
    Font dungeonUiFont = new Font("Times New Roman", Font.ITALIC, 20);
    Font navigationFont = new Font("Times New Roman", Font.BOLD, 50);

    public DungeonUIEXP() {
        this.setTitle("Dungeon Adventure");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 1000);
        myMainPanel = new JPanel();
        myMainPanel.setLayout(new GridLayout(0,1));
        myAdventureTextBox.setPreferredSize(new Dimension(20,0));
        returnButton = new JButton("RETURN TO TITLE SCREEN");
        setButtonColor(returnButton, Color.LIGHT_GRAY, Color.BLACK, dungeonUiFont);
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myAdventureTextBox.setText("");
                buildStartPanel();
            }
        });
        this.add(myMainPanel);
    }

    public void start() {
        buildStartPanel();
    }

    public void buildAdventurePanel(Room theCurrentRoom) {
        mainPanelReset();
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
            temp.setHorizontalAlignment(JTextField.CENTER);
            temp.setFont(navigationFont);
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
        JButton upButton = new JButton("N");
        setButtonColor(upButton, Color.LIGHT_GRAY, Color.BLACK, navigationFont);
        if (myController.getMyCurrRoom().getMyNorthRoom() == null) upButton.setEnabled(false);
        JButton leftButton = new JButton("W");
        setButtonColor(leftButton, Color.LIGHT_GRAY, Color.BLACK,  navigationFont);
        if (myController.getMyCurrRoom().getMyWestRoom() == null) leftButton.setEnabled(false);
        JButton rightButton = new JButton("E");
        setButtonColor(rightButton, Color.LIGHT_GRAY, Color.BLACK,  navigationFont);
        if (myController.getMyCurrRoom().getMyEastRoom() == null) rightButton.setEnabled(false);
        JButton downButton = new JButton("S");
        setButtonColor(downButton, Color.LIGHT_GRAY, Color.BLACK,  navigationFont);
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
            }
        });

        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myController.checkDirection("<");
            }
        });

        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myController.checkDirection(">");
            }
        });

        downButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myController.checkDirection("v");
            }
        });

    }

    public void buildInteractionsPanel() {
        JButton heroInfobutton = new JButton("Hero Info");
        setButtonColor(heroInfobutton, Color.LIGHT_GRAY, Color.BLACK, dungeonUiFont);
        JButton dungeonMapButton = new JButton("Dungeon Map");
        setButtonColor(dungeonMapButton, Color.LIGHT_GRAY, Color.BLACK, dungeonUiFont);
        JButton healPotionButton = new JButton("Use Heal Potion");
        setButtonColor(healPotionButton, Color.LIGHT_GRAY, Color.BLACK, dungeonUiFont);
        JButton visionPotionButton = new JButton("Use Vision Potion");
        setButtonColor(visionPotionButton, Color.LIGHT_GRAY, Color.BLACK, dungeonUiFont);
        JButton saveGameButton = new JButton("Save Game");
        setButtonColor(saveGameButton, Color.LIGHT_GRAY, Color.BLACK, dungeonUiFont);
        JButton quitGameButton = new JButton("Quit Game");
        setButtonColor(quitGameButton, Color.LIGHT_GRAY, Color.BLACK, dungeonUiFont);

        myInteractionsPanel = new JPanel();
        myInteractionsPanel.setLayout(new BoxLayout(myInteractionsPanel, BoxLayout.Y_AXIS));
        myInteractionsPanel.add(heroInfobutton);
        myInteractionsPanel.add(dungeonMapButton);
        myInteractionsPanel.add(healPotionButton);
        myInteractionsPanel.add(visionPotionButton);
        myInteractionsPanel.add(saveGameButton);
        myInteractionsPanel.add(quitGameButton);
        myInteractionsPanel.setBorder(BorderFactory.createTitledBorder("Options"));

        heroInfobutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HeroInfoWindow(myController.getMyHero());
            }
        });

        dungeonMapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DungeonMapWindow(myController.getMyDungeon());
            }
        });

        healPotionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myController.useHealPotion();
            }
        });

        visionPotionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myController.useVisionPotion();
            }
        });

        saveGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveGame();
            }
        });

        quitGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public void buildStartPanel() {
        mainPanelReset();
        JLabel titleScreenLabel = new JLabel();
        titleScreenLabel.setIcon(new ImageIcon(new ImageIcon("src/TitleScreen.png").getImage().getScaledInstance(1000, 1000, Image.SCALE_SMOOTH)));
        JButton startButton = new JButton("START NEW GAME");
        setButtonColor(startButton, Color.LIGHT_GRAY, Color.BLACK, dungeonUiFont);
        JButton loadButton = new JButton("LOAD GAME");
        setButtonColor(loadButton, Color.LIGHT_GRAY, Color.BLACK, dungeonUiFont);
        JButton quitButton = new JButton("QUIT GAME");
        setButtonColor(quitButton, Color.LIGHT_GRAY, Color.BLACK, dungeonUiFont);
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
                loadGame();
            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        myStartPanel.add(titleScreenLabel);
        myStartPanel.setBackground(Color.DARK_GRAY);
        myMainPanel.add(myStartPanel);
        this.setVisible(true);
    }

    public void buildOptionsPanel() {
        mainPanelReset();
        myOptionsPanel = new JPanel();
        myOptionsPanel.setLayout(new GridLayout(4,1));

        JTextField nameField = new JTextField();
        nameField.setFont(dungeonUiFont);
        nameField.setText("");
        myOptionsPanel.add(nameField);

        JComboBox<String> heroSelectBox = new JComboBox<>(myHeroes);
        heroSelectBox.setSelectedItem(myHeroes[0]);
        setComboBoxColor(heroSelectBox, Color.LIGHT_GRAY, Color.BLACK, dungeonUiFont);
        heroSelectBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myUserHero = (String)heroSelectBox.getSelectedItem();
            }
        });
        myOptionsPanel.add(heroSelectBox);

        JComboBox<Integer> dungeonSize = new JComboBox<>(mySizes);
        dungeonSize.setSelectedItem(mySizes[2]);
        setComboBoxColor(dungeonSize, Color.LIGHT_GRAY, Color.BLACK, dungeonUiFont);
        dungeonSize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myDungeonSize = (int) dungeonSize.getSelectedItem();
            }
        });
        myOptionsPanel.add(dungeonSize);

        JComboBox<String> difficultySelectBox = new JComboBox<>(myDifficulty);
        difficultySelectBox.setSelectedItem(myDifficulty[1]);
        setComboBoxColor(difficultySelectBox, Color.LIGHT_GRAY, Color.BLACK, dungeonUiFont);
        difficultySelectBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myUserDifficulty = (String)difficultySelectBox.getSelectedItem();
            }
        });
        myOptionsPanel.add(difficultySelectBox);

        JButton startAdventureButton = new JButton("Start Adventure");
        setButtonColor(startAdventureButton, Color.LIGHT_GRAY, Color.BLACK, dungeonUiFont);
        startAdventureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String heroName = nameField.getText();
                myController.startGame(heroName, myUserHero, myUserDifficulty, myDungeonSize);
                if (heroName.equals("GOD")) { //Cheat Code: Enter name as "GOD" to activate god mode
                    myController.getMyHero().setMyHitChance(1.0);
                    myController.getMyHero().setMyMinDam(500);
                    myController.getMyHero().setMyMaxDam(501);
                    myController.getMyHero().setMyBlockChance(1.0);
                }
                buildAdventurePanel(myController.getMyCurrRoom());
            }
        });
        myOptionsPanel.add(startAdventureButton);

        myMainPanel.add(myOptionsPanel);
        this.setVisible(true);
    }

    public void buildBattlePanel(Hero theHero, Monster theMonster) {
        mainPanelReset();
        myBattlePanel = new JPanel();
        myBattlePanel.setLayout(new GridLayout(0,3));
        JPanel heroBattlePanel = new JPanel();
        heroBattlePanel.setLayout(new GridLayout(3,1));
        heroBattlePanel.add(new JLabel(new ImageIcon(new ImageIcon("src/" + myUserHero + "Image.png").getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH))));
        JTextField heroHP = new JTextField(theHero.getMyName() + "'s HP: " + theHero.getMyHitPoints());
        heroHP.setEditable(false);
        heroHP.setFont(dungeonUiFont);
        heroBattlePanel.add(heroHP);

        JPanel heroBattleOptions = new JPanel();
        JButton attackButton = new JButton("ATTACK");
        setButtonColor(attackButton, Color.LIGHT_GRAY, Color.BLACK, dungeonUiFont);
        heroBattleOptions.add(attackButton);
        JButton skillButton = new JButton(theHero.getMySkillName());
        setButtonColor(skillButton, Color.LIGHT_GRAY, Color.BLACK, dungeonUiFont);
        heroBattleOptions.add(skillButton);
        JButton useHealButton = new JButton("USE HEAL POTION: " + theHero.getMyHealingPotions());
        if (theHero.getMyHealingPotions() < 1) useHealButton.setEnabled(false);
        setButtonColor(useHealButton, Color.LIGHT_GRAY, Color.BLACK, dungeonUiFont);
        heroBattleOptions.add(useHealButton);
        heroBattlePanel.add(heroBattleOptions);
        myBattlePanel.add(heroBattlePanel);

        myAdventureTextBox.setEditable(false);
        myBattlePanel.add(myAdventureTextBox);

        JPanel monsterBattlePanel = new JPanel();
        monsterBattlePanel.setLayout(new GridLayout(2,1));
        monsterBattlePanel.add(new JLabel(new ImageIcon(new ImageIcon("src/" + theMonster.getMyName() + "Image.jpeg").getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH))));
        JTextField monsterHP = new JTextField(theMonster.getMyName() + "'s HP: " + theMonster.getMyHitPoints());
        monsterHP.setEditable(false);
        monsterHP.setFont(dungeonUiFont);
        monsterBattlePanel.add(monsterHP);
        myBattlePanel.add(monsterBattlePanel);

        myMainPanel.add(myBattlePanel);
        this.setVisible(true);

        attackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myController.battle(theMonster, "ATTACK");
            }
        });
        skillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myController.battle(theMonster, "SKILL");
            }
        });
        useHealButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myController.battle(theMonster, "HEAL");
            }
        });
    }

    public void buildVictoryScreen() {
        mainPanelReset();
        JLabel victoryScreenLabel = new JLabel();
        JPanel victoryPanel = new JPanel();
        victoryPanel.setBackground(Color.BLACK);
        victoryScreenLabel.setIcon(new ImageIcon(new ImageIcon("src/VictoryScreen.png").getImage().getScaledInstance(1000, 1000, Image.SCALE_SMOOTH)));
        victoryPanel.add(returnButton);
        victoryPanel.add(victoryScreenLabel);
        myMainPanel.add(victoryPanel);
        this.setVisible(true);
    }

    public void buildDefeatScreen() {
        mainPanelReset();
        JLabel defeatScreenLabel = new JLabel();
        JPanel defeatPanel = new JPanel();
        defeatPanel.setBackground(Color.BLACK);
        defeatScreenLabel.setIcon(new ImageIcon(new ImageIcon("src/DefeatScreen.png").getImage().getScaledInstance(1000, 1000, Image.SCALE_SMOOTH)));
        defeatPanel.add(returnButton);
        defeatPanel.add(defeatScreenLabel);
        myMainPanel.add(defeatPanel);
        this.setVisible(true);
    }

    public void updateAdventureText(String newText) {
        try {
            myAdventureTextBox.getDocument().insertString(0, newText + "\n", null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public void setButtonColor(JButton theButton, Color theBackColor, Color theForeColor, Font theFont) {
        theButton.setBackground(theBackColor);
        theButton.setFont(theFont);
        theButton.setForeground(theForeColor);
    }

    public void setComboBoxColor(JComboBox theBox, Color theBackColor, Color theForeColor, Font theFont) {
        theBox.setBackground(theBackColor);
        theBox.setFont(theFont);
        theBox.setForeground(theForeColor);
    }

    public void mainPanelReset() {
        if(myMainPanel != null) {
            myMainPanel.removeAll();
            myMainPanel.repaint();
        }
    }

    public JPanel getMyMainPanel() {
        return myMainPanel;
    }

    public void saveGame() {
        try {
            FileOutputStream fileOut = new FileOutputStream("src/savefile.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(myController);
            out.close();
            fileOut.close();
            System.out.println("Your game has been saved");
        } catch (IOException i) {
            i.printStackTrace();
        }
        myController = null;
    }

    public void loadGame() {
        try {
            FileInputStream fileIn = new FileInputStream("src/savefile.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            myController = (ControllerEXP) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Controller class not found");
            c.printStackTrace();
        }
        buildAdventurePanel(myController.getMyCurrRoom());
    }

}
