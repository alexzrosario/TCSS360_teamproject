package main;

import main.DungeonGUI.DungeonUI;
import main.DungeonMain.DungeonAdventure;

public class Main {

    public static void main(String[] args) {
        DungeonUI v = new DungeonUI();
        // Uncomment and comment DungeonUI version to test the console version
        //DungeonAdventure v = new DungeonAdventure();
        v.start();
    }
}
