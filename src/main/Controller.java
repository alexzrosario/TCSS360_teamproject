package main;

import main.DungeonCharacter.*;
import main.DungeonMain.*;

import java.util.Random;
import java.util.Scanner;

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

    public boolean isAlive() {
        return myHero.getMyAlive();
    }

    public void checkRoom() {
        Room room;
        while(myHero.getMyAlive()) {
            room = getMyCurrRoom();
            if(room.isHasAbstractionPillar()) {
                myHero.setMyPillars(myHero.getMyPillars() + 1);
            }
            if(room.isHasEncapsulationPillar()) {
                myHero.setMyPillars(myHero.getMyPillars() + 1);
            }
            if(room.isHasInheritancePillar()) {
                myHero.setMyPillars(myHero.getMyPillars() + 1);
            }
            if(room.isHasPolymorphismPillar()) {
                myHero.setMyPillars(myHero.getMyPillars() + 1);
            }
            if(room.isHasPit()) {
                myHero.updateHealth(myHero.pitDamage());
            }
            if(room.isHasHealPotion()) {
                myHero.setMyHealingPotions(myHero.getMyHealingPotions() - 1);
            }
            if(room.isHasVisionPotion()) {
                myHero.setMyVisionPotions(myHero.getMyVisionPotions() + 1);
            }
            if(room.isHasMonster()) {
                Monster theMonster = room.getMyMonster();
                battle(myHero, theMonster);
                room.setMyMonster(null);
            }
            if(room.isExit()) {
                if(myHero.getMyPillars() < 4) {
                    System.out.println("You have not collected all the pillars!");
                }
                else {
                    myGameDone = true;
                }
            }
        }
    }

    private void battle(Hero theHero, Monster theMonster) {
        Scanner scan = new Scanner(System.in);
        int myChoice;
        while(theHero.getMyAlive() && theMonster.getMyAlive()) {
            System.out.println("Attack: 1");
            System.out.println("Special Attack: 2");
            System.out.println("Use Health Potion: 3\n" + "Potions Remaining: " + theHero.getMyHealingPotions());
            myChoice = scan.nextInt();
            if(myChoice == 3 && theHero.getMyHealingPotions() > 0) {
                useHealPotion();
            } else if (myChoice == 3 && theHero.getMyHealingPotions() == 0){
                System.out.println("You have no health potions remaining!");
            }
            theHero.battleMenu(theMonster, myChoice);
            if(theMonster.getMyAlive()) {
                theMonster.basicAttack(theHero);
                if(!theHero.getMyAlive()) {
                    myGameDone = true;
                }
            }
        }
    }

    public void saveGame() {

    }

    public void loadGame() {

    }

//    public class ChoiceController implements ActionListener {
//        public void actionPerformed(ActionEvent event) {
//            String choice = event.getActionCommand();
//            um.titleScreen();
//            switch (choice) {
//                case "start" -> um.nameInputScreen();
//                case "name" -> um.heroSelectScreen();
//                case "hero" -> um.dungeonRoomScreen();
//            }
//        }
//    }
}