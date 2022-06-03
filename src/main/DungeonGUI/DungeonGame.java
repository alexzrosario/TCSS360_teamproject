package main.DungeonGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DungeonGame {
    DungeonUI ui = new DungeonUI();
    public static void main(String[] args) throws IOException {
        new DungeonGame();
    }

    public DungeonGame() throws IOException {
        ui.DungeonUI();
    }
}