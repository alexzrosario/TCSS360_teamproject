package main.DungeonGUI;

import main.DungeonMain.Dungeon;

import javax.swing.*;
import java.awt.*;

/**
 * The type Dungeon map window.
 */
public class DungeonMapWindow extends JFrame {
    /**
     * Instantiates a new Dungeon map window.
     *
     * @param theDungeon the the dungeon
     */
    public DungeonMapWindow(Dungeon theDungeon) {
        this.setTitle("Hero Info");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(600, 600);

        JTextArea dungeonMapLabel = buildDungeonMapLabel(theDungeon);
        this.add(dungeonMapLabel);
        this.setVisible(true);
    }

    private JTextArea buildDungeonMapLabel(Dungeon theDungeon) {
        Font dungeonRoomFont = new Font(Font.MONOSPACED, Font.BOLD, 180/theDungeon.getMyDungeonCols());
        JTextArea dungeonMap = new JTextArea();
        dungeonMap.setEditable(false);
        dungeonMap.setFont(dungeonRoomFont);
        dungeonMap.setText(theDungeon.toString());
        return dungeonMap;
    }
}
