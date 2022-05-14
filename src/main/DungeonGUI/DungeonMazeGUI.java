package main.DungeonGUI;

import main.DungeonMain.Dungeon;
import main.DungeonMain.Room;

import javax.swing.*;
import java.awt.*;

public class DungeonMazeGUI extends JPanel {
    Dungeon myDungeon = new Dungeon(5,5);
    public void drawDungeon(Graphics2D g) {
        Graphics2D g2 = g;

        myDungeon.buildDungeonArray();
        myDungeon.generateDungeon();

        for(int i = 0; i < myDungeon.getMyDungeonRows(); i++) {
            for(int j = 0; i < myDungeon.getMyDungeonCols(); j++) {
                g2.drawLine(j * myDungeon.getMyDungeonRows() + myDungeon.getMyDungeonCols(),i *  myDungeon.getMyDungeonRows(), myDungeon.getMyDungeonRows() + j * myDungeon.getMyDungeonCols(), myDungeon.getMyDungeonRows() + i * myDungeon.getMyDungeonCols());
            }
        }
    }
    public void paintComponent(Graphics2D g) {
        super.paintComponent(g);
        drawDungeon(g);
    }
}
