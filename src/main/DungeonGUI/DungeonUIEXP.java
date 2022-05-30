package main.DungeonGUI;

import main.Controller;
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
////////////////////////////////////////////
    Hero myHero;
    Controller myController;

    JPanel myMainPanel;
    JPanel myDungeonPanel;
    JPanel myAdventurePanel;
    JPanel myNavigationPanel;
    JPanel myInteractionsPanel;
    JTextField myAdventureText;
    JTextField myBlankTextFieldWhite = new JTextField();
    JTextField myBlankTextFieldBlack = new JTextField();

    JButton testButton;
    Font gameTitleFont;
    Font regularFont = new Font("Times New Roman", Font.PLAIN, 20);

    public DungeonUIEXP(Room theCurrentRoom, Hero theHero) {
        this.myHero = theHero;
        this.setTitle("Dungeon Adventure");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 1000);
        myMainPanel = new JPanel();
        myMainPanel.setBackground(Color.WHITE);
        myMainPanel.setLayout(new BoxLayout(myMainPanel, BoxLayout.Y_AXIS));
        //myMainPanel.setLayout(new GridLayout(0, 1));
        setBlankTextFields();

        myDungeonPanel = dungeonPanel(theCurrentRoom, theHero);
        myMainPanel.add(myDungeonPanel);

        myAdventurePanel = new JPanel();
        myAdventurePanel.setLayout(new GridLayout(1, 3));
        myAdventureText = new JTextField();
        myAdventureText.setEditable(false);
        myAdventurePanel.add(myAdventureText);

        buildNavigationPanel();
        myAdventurePanel.add(myNavigationPanel);

        buildInteractionsPanel();
        myAdventurePanel.add(myInteractionsPanel);
        myMainPanel.add(myAdventurePanel);

        this.add(myMainPanel);
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
}
