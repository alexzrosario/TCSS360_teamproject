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
                myGameDone = false;
                break;
            case 2: // priestess
                myHero = new Priestess(theName);
                myGameDone = false;
                break;
            case 3: // thief
                myHero = new Thief(theName);
                myGameDone = false;
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
        if(myHero.getMyHealingPotions()>0 && myHero.getMyHitPoints() < myHero.getMY_MAX_HEALTH()){
            int heal = r.nextInt((int) (.05*maxHealth),(int) (.1*maxHealth)); // can change later
            myHero.setMyHealingPotions(myHero.getMyHealingPotions()-1);
            //call to add heal from healing potion
            myHero.setMyHitPoints(Math.min((myHero.getMyHitPoints() + heal), myHero.getMY_MAX_HEALTH()));
        }
        else if (myHero.getMyHealingPotions()>0 && myHero.getMyHitPoints() == myHero.getMY_MAX_HEALTH())
            System.out.println("You are already at full health");
        else System.out.println("No Healing Potions To Use");
    }

    public void useVisionPotion() {
        if (myHero.getMyVisionPotions() > 0) {
            if (myCurrRoom.getMyNorthRoom() != null) {
                myCurrRoom.getMyNorthRoom().setSeen(true);
            }
            if (myCurrRoom.getMySouthRoom() != null) {
                myCurrRoom.getMySouthRoom().setSeen(true);
            }
            if (myCurrRoom.getMyWestRoom() != null) {
                myCurrRoom.getMyWestRoom().setSeen(true);
            }
            if (myCurrRoom.getMyEastRoom() != null) {
                myCurrRoom.getMyEastRoom().setSeen(true);
            }
            if (myCurrRoom.getMyNorthRoom() != null && myCurrRoom.getMyNorthRoom().getMyWestRoom() != null) {
                myCurrRoom.getMyNorthRoom().getMyWestRoom().setSeen(true);
            }
            if (myCurrRoom.getMySouthRoom() != null && myCurrRoom.getMySouthRoom().getMyWestRoom() != null) {
                myCurrRoom.getMySouthRoom().getMyWestRoom().setSeen(true);
            }
            if (myCurrRoom.getMyNorthRoom() != null && myCurrRoom.getMyNorthRoom().getMyEastRoom() != null) {
                myCurrRoom.getMyNorthRoom().getMyEastRoom().setSeen(true);
            }
            if (myCurrRoom.getMySouthRoom() != null && myCurrRoom.getMySouthRoom().getMyEastRoom() != null) {
                myCurrRoom.getMySouthRoom().getMyEastRoom().setSeen(true);
            }
            myHero.setMyVisionPotions(myHero.getMyVisionPotions() - 1);
        }
        else System.out.println("No Vision Potions To Use");
    }

    public int pitDamage() {
        Random r = new Random();
        int damageRoll = 0;
        damageRoll = r.nextInt(1, 15) + 1;
        return damageRoll;
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
        boolean inventoryHelper = true;
        Scanner scan = new Scanner(System.in);
        while (inventoryHelper){
            System.out.println("\nHealing Potions: " + myHero.getMyHealingPotions());
            System.out.println("Vision Potions: " + myHero.getMyVisionPotions());
            System.out.println("Number of Pillars: " + myHero.getMyPillars() + "\n");
            System.out.println("B to use a Healing Potion");
            System.out.println("V to use a Vision Potion");
            System.out.println("C to close the inventory screen");
            String input = scan.next();
            switch (input){
                case "B" :
                    useHealPotion();
                    break;
                case "V" :
                    useVisionPotion();
                    break;
                case "C" :
                    inventoryHelper = false;
            }
        }
        System.out.println(myDungeon);
    }

    public boolean isAlive() {
        return myHero.getMyAlive();
    }

    public void traverse() {
        Scanner scan = new Scanner(System.in);
        //boolean gameNotDone = true;
        String dir;
        while (!myGameDone)   {
            System.out.println("Select an option to move the following:\n");
            System.out.println("N for North, S for South, E for East, or W for West");
            System.out.println("H to see hero info");
            System.out.println("I to open hero inventory");
            dir = scan.next();
            switch (dir){
                case "N" :
                    /*if (myCurrRoom.getMyNorthRoom() != null) {
                        moveNorth();
                        checkRoom();
                    }*/
                    moveNorth();
                    checkRoom();
                    break;
                case "S" :
                    /*if (myCurrRoom.getMySouthRoom() != null) {
                        moveSouth();
                        checkRoom();
                    }*/
                    moveSouth();
                    checkRoom();
                    break;
                case "E":
                    /*if (myCurrRoom.getMyEastRoom() != null) {
                        moveEast();
                        checkRoom();
                    }*/
                    moveEast();
                    checkRoom();
                    break;
                case "W":
                    /*if (myCurrRoom.getMyWestRoom() != null) {
                        moveWest();
                        checkRoom();
                    }*/
                    moveWest();
                    checkRoom();
                    break;
                case "H":
                    heroInfo();
                    break;
                case "I":
                    heroInventory();
                    break;
                default:
                    break;
            }
        }
    }

    public void checkRoom() {
        Room room;
        while(!myGameDone) {
            room = getMyCurrRoom();
            if(room.isHasAbstractionPillar()) {
                myHero.setMyPillars(myHero.getMyPillars() + 1);
                System.out.println("You have found the Pillar of Abstraction!");
                room.setHasAbstractionPillar(false);
            }
            if(room.isHasEncapsulationPillar()) {
                myHero.setMyPillars(myHero.getMyPillars() + 1);
                System.out.println("You have found the Pillar of Encapsulation!");
                room.setHasEncapsulationPillar(false);
            }
            if(room.isHasInheritancePillar()) {
                myHero.setMyPillars(myHero.getMyPillars() + 1);
                System.out.println("You have found the Pillar of Inheritance!");
                room.setHasInheritancePillar(false);
            }
            if(room.isHasPolymorphismPillar()) {
                myHero.setMyPillars(myHero.getMyPillars() + 1);
                System.out.println("You have found the Pillar of Polymorphism!");
                room.setHasPolymorphismPillar(false);
            }
            if(room.isHasPit()) {
                int pitDamageTaken = pitDamage();
                double tempBlockChance = myHero.getMyBlockChance();
                myHero.setMyBlockChance(0.0);
                myHero.updateHealth(pitDamageTaken);
                myHero.setMyBlockChance(tempBlockChance);
                System.out.println("You have fallen into a pit and have taken " + pitDamageTaken + " damage!");
                room.setHasPit(false);
                if (!myHero.getMyAlive()) myGameDone = true;
            }
            if(room.isHasHealPotion()) {
                myHero.setMyHealingPotions(myHero.getMyHealingPotions() + 1);
                System.out.println("You have found a healing potion!");
                room.setHasHealPotion(false);
            }
            if(room.isHasVisionPotion()) {
                myHero.setMyVisionPotions(myHero.getMyVisionPotions() + 1);
                System.out.println("You have found a vision potion!");
                room.setHasVisionPotion(false);
            }
            if(room.isHasMonster()) {
                Monster theMonster = room.getMyMonster();
                System.out.println("You have encountered a " + theMonster.getMyName() + "!");
                battle(myHero, theMonster);
                room.setMyMonster(null);
                room.setHasMonster(false);
                System.out.println(myDungeon);
            }
            if(room.isExit()) {
                if(myHero.getMyPillars() < 4) {
                    System.out.println("You have not collected all the pillars!");
                }
                else {
                    myGameDone = true;
                    System.out.println("you win!");
                }
            }
            traverse();
        }
    }

    private void battle(Hero theHero, Monster theMonster) {
        Scanner scan = new Scanner(System.in);
        int myChoice;
        while(theHero.getMyAlive() && theMonster.getMyAlive()) {
            System.out.println(theHero.getMyName() + " health: " + theHero.getMyHitPoints());
            System.out.println(theMonster.getMyName() + " health: " + theMonster.getMyHitPoints());
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