package main;

import main.DungeonCharacter.*;
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

    public void startGame(final String theName, final String theClass, final String theDifficulty, int theDungeonSize) {
        if (theClass.equals("Warrior")) {
            myHero = new Warrior(theName);
            myGameDone = false;
        }
        else if (theClass.equals("Priestess")) {
            myHero = new Priestess(theName);
            myGameDone = false;
        }
        else if (theClass.equals("Thief")) {
            myHero = new Thief(theName);
            myGameDone = false;
        }
        else if (theClass.equals("Barbarian")) {
            myHero = new Barbarian(theName);
            myGameDone = false;
        }
        else if (theClass.equals("Mage")) {
            myHero = new Mage(theName);
            myGameDone = false;
        }
        else if (theClass.equals("Swordsman")) {
            myHero = new Swordsman(theName);
            myGameDone = false;
        }
        else if (theClass.equals("Monk")) {
            myHero = new Monk(theName);
            myGameDone = false;
        }
        else if (theClass.equals("Samurai")) {
            myHero = new Samurai(theName);
            myGameDone = false;
        }
        else if (theClass.equals("Occultist")){
            myHero = new Occultist(theName);
            myGameDone = false;
        }

        myDungeon = new Dungeon(theDungeonSize, theDungeonSize, theDifficulty);
        myCurrRoom = myDungeon.getMyRoom();
    }

    public Dungeon getMyDungeon() {
        return myDungeon;
    }

    public void setMyDungeon(Dungeon myDungeon) {
        this.myDungeon = myDungeon;
    }

    public Room getMyCurrRoom() {
        return myCurrRoom;
    }

    public void setMyCurrRoom(Room myCurrRoom) {
        this.myCurrRoom = myCurrRoom;
    }

    public Hero getMyHero() {
        return myHero;
    }

    public void setMyHero(Hero myHero) {
        this.myHero = myHero;
    }

    public boolean isMyGameDone() {
        return myGameDone;
    }

    public void setMyGameDone(boolean myGameDone) {
        this.myGameDone = myGameDone;
    }
}
