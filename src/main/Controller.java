package main;

import main.DungeonCharacter.*;
import main.DungeonMain.*;

import java.util.Random;

public class Controller {
    private Dungeon myDungeon;
    private Room myCurrRoom;
    private Hero myHero;
    private boolean myGameDone = false;
    private Random r = new Random();

    public Controller(){}

    public void startGame(final String theName, final int theClass){
        switch (theClass){
            case 1: // warrior
                myHero = new Warrior(theName);
                break;
            case 2: // priestess
                myHero = new Priestess(theName);
                break;
            case 3: // thief
                myHero = new Thief(theName);
                break;
        }

        myDungeon = new Dungeon(5,5);
        System.out.println(myDungeon);
        System.out.println(myHero.getMyName());
        myCurrRoom = myDungeon.getMyRoom();
    }

    public void traverseDungeon(){
        myCurrRoom = myDungeon.getMyRoom();
    }

    public void moveNorth(){
        myDungeon.moveHero(-1,0);
        traverseDungeon();
    }
    public void moveSouth(){
        myDungeon.moveHero(1,0);
        traverseDungeon();
    }
    public void moveEast(){
        myDungeon.moveHero(0,1);
        traverseDungeon();
    }
    public void moveWest(){
        myDungeon.moveHero(0,-1);
        traverseDungeon();
    }

    public void useHealPotion(){
        int maxHealth = myHero.getMY_MAX_HEALTH();
        if(myHero.getMyHealingPotions()>0){
            int heal = r.nextInt((int) (.05*maxHealth),(int) (.1*maxHealth)); // can change later
            myHero.setMyHealingPotions(myHero.getMyHealingPotions()-1);
            //call to add heal from healing potion
            myHero.setMyHitPoints(Math.min((myHero.getMyHitPoints() + heal), myHero.getMY_MAX_HEALTH()));
        }
        System.out.println("No Healing Potions To Use");
    }

    public Room getMyCurrRoom() {
        return myCurrRoom;
    }
}