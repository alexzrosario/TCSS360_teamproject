package main;

import main.DungeonCharacter.*;
import main.DungeonGUI.DungeonUIEXP;
import main.DungeonMain.Dungeon;
import main.DungeonMain.DungeonAdventure;
import main.DungeonMain.Room;

import javax.sound.sampled.Clip;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

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
        System.out.println(myDungeon);
        myCurrRoom = myDungeon.getMyRoom();
    }

    public void checkDirection(String theDirection) {
        if (theDirection.equals("^")) {
            traverseDungeon(-1,0);
        }
        if (theDirection.equals("v")) {
            traverseDungeon(1,0);
        }
        if (theDirection.equals(">")) {
            traverseDungeon(0,1);
        }
        if (theDirection.equals("<")) {
            traverseDungeon(0,-1);
        }
    }

    public void traverseDungeon(int rowDir, int colDir) {
        myDungeon.moveHero(rowDir,colDir);
        myCurrRoom = myDungeon.getMyRoom();
        checkRoom();
    }

    public void checkRoom() {
        Room room = myCurrRoom;
        if(room.isHasAbstractionPillar()) {
            myHero.setMyPillars(myHero.getMyPillars() + 1);
            myDungeonUIEXP.updateAdventureText("You have found the Pillar of Abstraction!");
            room.setHasAbstractionPillar(false);
        }
        if(room.isHasEncapsulationPillar()) {
            myHero.setMyPillars(myHero.getMyPillars() + 1);
            myDungeonUIEXP.updateAdventureText("You have found the Pillar of Encapsulation!");
            room.setHasEncapsulationPillar(false);
        }
        if(room.isHasInheritancePillar()) {
            myHero.setMyPillars(myHero.getMyPillars() + 1);
            myDungeonUIEXP.updateAdventureText("You have found the Pillar of Inheritance!");
            room.setHasInheritancePillar(false);
        }
        if(room.isHasPolymorphismPillar()) {
            myHero.setMyPillars(myHero.getMyPillars() + 1);
            myDungeonUIEXP.updateAdventureText("You have found the Pillar of Polymorphism!");
            room.setHasPolymorphismPillar(false);
        }
        if(room.isHasPit()) {
            int pitDamageTaken = pitDamage();
            double tempBlockChance = myHero.getMyBlockChance();
            myHero.setMyBlockChance(0.0);
            myHero.updateHealth(pitDamageTaken);
//                playAudio("src/playerhurt.wav");
            myHero.setMyBlockChance(tempBlockChance);
            myDungeonUIEXP.updateAdventureText("You have fallen into a pit and have taken " + pitDamageTaken + " damage!");
            room.setHasPit(false);
            if (!myHero.getMyAlive()) {
                myGameDone = true;
                gameover();
            }
        }
        if(room.isHasHealPotion()) {
            myHero.setMyHealingPotions(myHero.getMyHealingPotions() + 1);
            myDungeonUIEXP.updateAdventureText("You have found a healing potion!");
            room.setHasHealPotion(false);
        }
        if(room.isHasVisionPotion()) {
            myHero.setMyVisionPotions(myHero.getMyVisionPotions() + 1);
            myDungeonUIEXP.updateAdventureText("You have found a vision potion!");
            room.setHasVisionPotion(false);
        }
        if(room.isHasMonster()) {
            //backgroundClip.stop();
            //playBattleAudio();
            Monster theMonster = room.getMyMonster();
            myDungeonUIEXP.updateAdventureText("You have encountered a " + theMonster.getMyName() + "!");
            battle(myHero, theMonster);
            room.setMyMonster(null);
            room.setHasMonster(false);
            //battleClip.stop();
            if(myHero.getMyAlive()) {
                System.out.println(myDungeon);
                //backgroundClip.start();
            }
        }
        else {
            myDungeonUIEXP.buildAdventurePanel(getMyCurrRoom());
        }
        if(room.isExit()) {
            if(myHero.getMyPillars() < 4) {
                myDungeonUIEXP.updateAdventureText("You have not collected all the pillars!");
            }
            else {
                System.out.println("You have collected all the pillars!");
                System.out.println("However, one last challenge stands in your way.");
                Monster theMonster = new MonsterFactory().createMonster("Lord of OO");
                System.out.println("You have encountered a " + theMonster.getMyName() + "!");
                battle(myHero, theMonster);
                myGameDone = true;
                if(!myHero.getMyAlive()) {
                    gameover();
                }else{
                    victory();
                }
            }
        }
    }

    private void battle(Hero theHero, Monster theMonster) {
        Scanner scan = new Scanner(System.in);
        String myChoice;
        if(theHero.getMyAlive() && theMonster.getMyAlive()) {
            myDungeonUIEXP.buildBattlePanel(theHero, theMonster);
            /*myChoice = scan.nextInt();
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
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    gameover();
                }
            }*/
        }
    }

    public void useHealPotion(){
        int maxHealth = myHero.getMY_MAX_HEALTH();
        if(myHero.getMyHealingPotions()>0 && myHero.getMyHitPoints() < myHero.getMY_MAX_HEALTH()){
            int heal = r.nextInt((int) (.14*maxHealth),(int) (.28*maxHealth)); // can change later
            myHero.setMyHealingPotions(myHero.getMyHealingPotions()-1);
            //call to add heal from healing potion
            myHero.setMyHitPoints(Math.min((myHero.getMyHitPoints() + heal), myHero.getMY_MAX_HEALTH()));
            myDungeonUIEXP.updateAdventureText("You healed for " + heal + " health");
        }
        else if (myHero.getMyHealingPotions()>0 && myHero.getMyHitPoints() == myHero.getMY_MAX_HEALTH())
            myDungeonUIEXP.updateAdventureText("You are already at full health");
        else myDungeonUIEXP.updateAdventureText("No Healing Potions To Use");
    }

    public int pitDamage() {
        Random r = new Random();
        return r.nextInt(1, 15) + 1;
    }


    public void victory() {
        try(BufferedReader br = new BufferedReader(new FileReader("src/victory.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void gameover() {
        try(BufferedReader br = new BufferedReader(new FileReader("src/gameover.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
