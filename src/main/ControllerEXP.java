package main;

import main.DungeonCharacter.*;
import main.DungeonGUI.DungeonUIEXP;
import main.DungeonMain.Dungeon;
import main.DungeonMain.DungeonAdventure;
import main.DungeonMain.Room;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class ControllerEXP implements Serializable{
    private Dungeon myDungeon;
    private Room myCurrRoom;
    private Hero myHero;
    private boolean myGameDone = false;
    private Random r = new Random();
    private DungeonUIEXP myDungeonUIEXP;
    private final AudioController audioController = new AudioController();
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
        audioController.playBackgroundAudio();
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
            audioController.playAudio("src/playerhurt2.wav");
            myHero.setMyBlockChance(tempBlockChance);
            myDungeonUIEXP.updateAdventureText("You have fallen into a pit and have taken " + pitDamageTaken + " damage!");
            room.setHasPit(false);
            if (!myHero.getMyAlive()) {
                myDungeonUIEXP.buildDefeatScreen();
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
            audioController.stopBackgroundAudio();
            audioController.playBattleAudio();
            Monster theMonster = room.getMyMonster();
            myDungeonUIEXP.updateAdventureText("You have encountered a " + theMonster.getMyName() + "!");
            myDungeonUIEXP.buildBattlePanel(myHero, theMonster);
            room.setMyMonster(null);
            room.setHasMonster(false);
        }
        else {
            myDungeonUIEXP.buildAdventurePanel(getMyCurrRoom());
        }
        if(room.isExit()) {
            if(myHero.getMyPillars() < 4) {
                myDungeonUIEXP.updateAdventureText("You have not collected all the pillars!");
            }
            else {
                audioController.stopBackgroundAudio();
                myDungeonUIEXP.updateAdventureText("You have collected all the pillars!");
                myDungeonUIEXP.getMyMainPanel().repaint();
                myDungeonUIEXP.updateAdventureText("However, one last challenge stands in your way.");
                myDungeonUIEXP.getMyMainPanel().repaint();
                Monster theMonster = new MonsterFactory().createMonster("Lord of OO");
                myDungeonUIEXP.updateAdventureText("You must now face the " + theMonster.getMyName() + "!");
                myDungeonUIEXP.getMyMainPanel().repaint();
                audioController.playBossAudio();
                myDungeonUIEXP.buildBattlePanel(myHero, theMonster);
            }
        }
    }

    public void battle(Monster theMonster, String theAction) {
        switch (theAction) {
            case "ATTACK" -> {
                myDungeonUIEXP.updateAdventureText(myHero.getMyName() + " attacks");
                basicAttack(myHero, theMonster);
            }
            case "SKILL" -> {
                myDungeonUIEXP.updateAdventureText(myHero.getMyName() + " uses " + myHero.getMySkillName());
                myHero.useSkill(theMonster);
            }
            case "HEAL" -> useHealPotion();
            default -> myDungeonUIEXP.updateAdventureText("Invalid Choice");
        }
        if (theMonster.getMyAlive()) {
            myDungeonUIEXP.updateAdventureText(theMonster.getMyName() + " attacks");
            theMonster.basicAttack(myHero);
            if(!myHero.getMyAlive() && theMonster.getMyName().equals("Lord of OO")) {
                audioController.stopBossAudio();
                audioController.playAudio("src/deathsound.wav");
                myDungeonUIEXP.buildDefeatScreen();
                return;
            } else if(!myHero.getMyAlive()) {
                audioController.stopBattleAudio();
                audioController.playAudio("src/deathsound.wav");
                myDungeonUIEXP.buildDefeatScreen();
                return;
            }
        }
        if (myHero instanceof StateResettable) ((StateResettable) myHero).resetState();

        if(myHero.getMyAlive() && theMonster.getMyAlive()) myDungeonUIEXP.buildBattlePanel(myHero, theMonster);
        else if (!theMonster.getMyAlive() && theMonster.getMyName().equals("Lord of OO")){
            audioController.stopBossAudio();
            audioController.playAudio("src/victorysound.wav");
            myDungeonUIEXP.buildVictoryScreen();
        }
        else {
            audioController.stopBattleAudio();
            audioController.startBackgroundAudio();
            myDungeonUIEXP.buildAdventurePanel(myCurrRoom);
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
            myHero.setMyVisionPotions(myHero.getMyVisionPotions() - 1);
            myDungeonUIEXP.updateAdventureText("You can see nearby traversable rooms");
        }
        else myDungeonUIEXP.updateAdventureText("No Vision Potions To Use");
        myDungeonUIEXP.buildAdventurePanel(getMyCurrRoom());
    }

    public int pitDamage() {
        Random r = new Random();
        return r.nextInt(1, 15) + 1;
    }

    public int basicAttack(final DungeonCharacter theAttacker, final DungeonCharacter theTarget){
        int attacks = 1;
        int tempTargetHealth = theTarget.getMyHitPoints();
        if(theAttacker.getMyAttackSpeed() > theTarget.getMyAttackSpeed()) {
            attacks = theAttacker.getMyAttackSpeed() / theTarget.getMyAttackSpeed();
        }
        for (int i = 0; i < attacks; i++) {
            int damage = theAttacker.attackValue(theTarget);
            if (damage > 0) {
                myDungeonUIEXP.updateAdventureText(theAttacker.getMyName() + " hits for " + damage + " damage");
                if(theTarget.getMyHitPoints() > tempTargetHealth - damage && theTarget instanceof Monster) {
                    myDungeonUIEXP.updateAdventureText(theTarget.getMyName() + " heals for " + ((theTarget.getMyHitPoints())-(tempTargetHealth - damage)) + " damage");
                }
            }
            else myDungeonUIEXP.updateAdventureText(theAttacker.getMyName() + " missed their attack");
        }
        return attacks;
    }

    private Object readResolve() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        audioController.setBackgroundClip();
        return this;
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