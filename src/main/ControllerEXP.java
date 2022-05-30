package main;

import main.DungeonCharacter.Hero;
import main.DungeonGUI.DungeonUIEXP;
import main.DungeonMain.Dungeon;
import main.DungeonMain.DungeonAdventure;
import main.DungeonMain.Room;

import javax.sound.sampled.Clip;
import java.util.Random;

public class ControllerEXP {
    private Dungeon myDungeon;
    private Room myCurrRoom;
    private Hero myHero;
    private boolean myGameDone = false;
    private Random r = new Random();
    private DungeonUIEXP myDungeonUIEXP;
    private transient Clip clip;
    private transient Clip backgroundClip;
    private transient Clip battleClip;
    private static final long serialVersionUID = 13425364675L;
    public ControllerEXP(DungeonUIEXP theDungeonUIEXP){
        myDungeonUIEXP = theDungeonUIEXP;
    }

}
