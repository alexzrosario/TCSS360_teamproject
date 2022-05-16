package main;

import main.DungeonCharacter.*;
import main.DungeonGUI.DungeonUI;
import main.DungeonGUI.DungeonUIManager;
import main.DungeonMain.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Controller {
    private Dungeon myDungeon;
    private Room myCurrRoom;
    private Hero myHero;
    private boolean myGameDone = false;
    private Random r = new Random();
    private DungeonUI ui = new DungeonUI();
    private DungeonUIManager um = new DungeonUIManager(ui);
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
        System.out.println(myDungeon);
    }
    public void moveSouth(){
        myDungeon.moveHero(1,0);
        traverseDungeon();
        System.out.println(myDungeon);
    }
    public void moveEast(){
        myDungeon.moveHero(0,1);
        traverseDungeon();
        System.out.println(myDungeon);
    }
    public void moveWest(){
        myDungeon.moveHero(0,-1);
        traverseDungeon();
        System.out.println(myDungeon);
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

    public void heroInfo() {
        System.out.println("\nName: " + myHero.getMyName());
        if(myHero instanceof Warrior){
            System.out.println("Class: Warrior");
        } else if(myHero instanceof Priestess){
            System.out.println("Class: Priestess");
        } else if(myHero instanceof Thief){
            System.out.println("Class: Thief");
        }
        System.out.println("HP: " + myHero.getMyHitPoints());
        System.out.println("Attack Speed: " + myHero.getMyAttackSpeed());
        System.out.println("Damage Range: " + myHero.getMyMinDam() +"-"+ myHero.getMyMaxDam());
        System.out.println("Hit Chance: " + myHero.getMyHitChance()+"\n");
    }

    public void heroInventory() {
        System.out.println("\nHealing Potions: " + myHero.getMyHealingPotions());
        System.out.println("Vision Potions: " + myHero.getMyVisionPotions());
        System.out.println("Number of Pillars: " + myHero.getMyPillars()+"\n");
    }

    public class ChoiceController implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String choice = event.getActionCommand();
            um.titleScreen();
            switch (choice) {
                case "start" -> um.nameInputScreen();
                case "name" -> um.heroSelectScreen();
                case "hero" -> um.dungeonRoomScreen();
            }
        }
    }
}