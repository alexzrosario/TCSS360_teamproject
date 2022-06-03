package main.DungeonGUI;

import main.DungeonMain.Dungeon;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class DungeonMapWindow extends JFrame {
    Font dungeonRoomFont = new Font(Font.MONOSPACED, Font.BOLD, 30);
    public DungeonMapWindow(Dungeon theDungeon) {
        this.setTitle("Hero Info");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(600, 600);

        JTextArea dungeonMapLabel = buildDungeonMapLabel(theDungeon);
        this.add(dungeonMapLabel);
        this.setVisible(true);
    }

    private JTextArea buildDungeonMapLabel(Dungeon theDungeon) {
        JTextArea dungeonMap = new JTextArea();
        dungeonMap.setEditable(false);
        dungeonMap.setFont(dungeonRoomFont);
        dungeonMap.setText(theDungeon.toString());
        return dungeonMap;
    }
}
