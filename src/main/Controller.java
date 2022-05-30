package main;

import main.DungeonCharacter.*;
import main.DungeonGUI.DungeonUIEXP;
import main.DungeonMain.Dungeon;
import main.DungeonMain.DungeonAdventure;
import main.DungeonMain.Room;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.*;
import java.util.Random;
import java.util.Scanner;


public class Controller implements Serializable{
    private Dungeon myDungeon;
    private Room myCurrRoom;
    private Hero myHero;
    private boolean myGameDone = false;
    private Random r = new Random();
    private DungeonAdventure myDungeonAdventure;
    private transient Clip clip;
    private transient Clip backgroundClip;
    private transient Clip battleClip;
    private static final long serialVersionUID = 13425364675L;
    public Controller(DungeonAdventure theDungeonAdventure){
        myDungeonAdventure = theDungeonAdventure;
    }

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
            case 4: // barbarian
                myHero = new Barbarian(theName);
                myGameDone = false;
                break;
            case 5: // mage
                myHero = new Mage(theName);
                myGameDone = false;
                break;
            case 6: // swordsman
                myHero = new Swordsman(theName);
                myGameDone = false;
                break;
            case 7: // Monk
                myHero = new Monk(theName);
                myGameDone = false;
                break;
            case 8: // samurai
                myHero = new Samurai(theName);
                myGameDone = false;
                break;
            case 9: // occultist
                myHero = new Occultist(theName);
                myGameDone = false;
                break;
        }
        int dungeonSize = myDungeonAdventure.dungeonSizePrompt();
        String dungeonDifficulty = myDungeonAdventure.difficultyPrompt();
        myDungeon = buildDungeon(dungeonSize, dungeonSize, dungeonDifficulty);
        //myDungeon = new Dungeon(5,5);
        System.out.println(myDungeon);
        System.out.println(myHero.getMyName());
        myCurrRoom = myDungeon.getMyRoom();
        playBackgroundAudio();
        DungeonUIEXP e = new DungeonUIEXP(myCurrRoom, myHero);
    }

    public Dungeon buildDungeon(int theRows, int theCols, String theDifficulty) {
        return new Dungeon(theRows, theCols, theDifficulty);
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
            int heal = r.nextInt((int) (.14*maxHealth),(int) (.28*maxHealth)); // can change later
            myHero.setMyHealingPotions(myHero.getMyHealingPotions()-1);
            //call to add heal from healing potion
            myHero.setMyHitPoints(Math.min((myHero.getMyHitPoints() + heal), myHero.getMY_MAX_HEALTH()));
            System.out.println("You healed for " + heal + " health");
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
            System.out.println("You can see nearby traversable rooms");
            System.out.println(myDungeon);
        }
        else System.out.println("No Vision Potions To Use");
    }

    public int pitDamage() {
        Random r = new Random();
        int damageRoll = r.nextInt(1, 15) + 1;
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
            System.out.println("S to Save Game");
            System.out.println("Q to Quit Game");
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
                    break;
                case "S" :
                    useSave();
                    break;
                case "Q" :
                    System.exit(0);
                    break;
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
            //System.out.println("N for North, S for South, E for East, or W for West");
            if (myCurrRoom.getMyNorthRoom() != null) {
                System.out.println("N to traverse North");
            }
            if (myCurrRoom.getMySouthRoom() != null) {
                System.out.println("S to traverse South");
            }
            if (myCurrRoom.getMyWestRoom() != null) {
                System.out.println("W to traverse West");
            }
            if (myCurrRoom.getMyEastRoom() != null) {
                System.out.println("E to traverse East");
            }
            System.out.println("H to see hero info");
            System.out.println("I to open hero inventory");
            dir = scan.next();
            switch (dir){
                case "N" :
                    if (myCurrRoom.getMyNorthRoom() != null) {
                        moveNorth();
                        checkRoom();
                    }
                    //moveNorth();
                    //checkRoom();
                    break;
                case "S" :
                    if (myCurrRoom.getMySouthRoom() != null) {
                        moveSouth();
                        checkRoom();
                    }
                    //moveSouth();
                    //checkRoom();
                    break;
                case "E":
                    if (myCurrRoom.getMyEastRoom() != null) {
                        moveEast();
                        checkRoom();
                    }
                    //moveEast();
                    //checkRoom();
                    break;
                case "W":
                    if (myCurrRoom.getMyWestRoom() != null) {
                        moveWest();
                        checkRoom();
                    }
                    //moveWest();
                    //checkRoom();
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
//                playAudio("src/playerhurt.wav");
                myHero.setMyBlockChance(tempBlockChance);
                System.out.println("You have fallen into a pit and have taken " + pitDamageTaken + " damage!");
                room.setHasPit(false);
                if (!myHero.getMyAlive()) {
                    myGameDone = true;
                    gameover();
                }
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
                backgroundClip.stop();
                playBattleAudio();
                Monster theMonster = room.getMyMonster();
                System.out.println("You have encountered a " + theMonster.getMyName() + "!");
                battle(myHero, theMonster);
                room.setMyMonster(null);
                room.setHasMonster(false);
                battleClip.stop();
                if(myHero.getMyAlive()) {
                    System.out.println(myDungeon);
                    backgroundClip.start();
                }
            }
            if(room.isExit()) {
                if(myHero.getMyPillars() < 4) {
                    System.out.println("You have not collected all the pillars!");

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
            System.out.println(myHero.getMySkillName() + ": 2");
            //System.out.println("Special Attack: 2");
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
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    gameover();
                }
            }
        }
    }

    public void useSave() {
        myDungeonAdventure.saveGame();
    }

    public void useLoad() {
        myDungeonAdventure.loadGame();
        System.out.println(myDungeon);
    }

    public Dungeon getMyDungeon() {
        return myDungeon;
    }

    public void setMyDungeon(Dungeon myDungeon) {
        this.myDungeon = myDungeon;
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

    public void playAudio(String audioFile) {
        try {
            File musicPath = new File(audioFile);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
            clip = AudioSystem.getClip();
            clip.open(audioInput);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-30.f);
            clip.start();
            if(audioFile.equals("src/backgroundmusic.wav") || audioFile.equals("src/battlemusic.wav")) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                Thread.sleep(clip.getMicrosecondLength()/1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void playBackgroundAudio() {
        try {
            File musicPath = new File("src/backgroundmusic.wav");
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
            backgroundClip = AudioSystem.getClip();
            backgroundClip.open(audioInput);
            FloatControl gainControl = (FloatControl) backgroundClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-30.f);
            backgroundClip.start();
            backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playBattleAudio() {
        try {
            File musicPath = new File("src/battlemusic.wav");
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
            battleClip = AudioSystem.getClip();
            battleClip.open(audioInput);
            FloatControl gainControl = (FloatControl) battleClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-30.f);
            battleClip.start();
            battleClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}