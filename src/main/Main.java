package main;

import main.DungeonGUI.DungeonUIEXP;
import main.DungeonMain.DungeonAdventure;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
//        DungeonAdventure v = new DungeonAdventure();
        DungeonUIEXP v = new DungeonUIEXP();
            v.start();
    }
}
