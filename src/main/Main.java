package main;

import main.DungeonGUI.DungeonUI;
import main.DungeonMain.DungeonAdventure;

public class Main {

    public static void main(String[] args) {
        DungeonUI v = new DungeonUI();
        //DungeonAdventure v = new DungeonAdventure(); //Uncomment and comment DungeonUI version to test the console version
        v.start();
    }
}
