package main;

import main.DungeonCharacter.*;
import main.DungeonMain.Dungeon;
import main.DungeonMain.DungeonAdventure;
import main.DungeonMain.Room;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;
import java.util.Random;
import java.util.Scanner;


/**
 * The type Console controller.
 */
public class ConsoleController implements Serializable{
    private Dungeon myDungeon;
    private Room myCurrRoom;
    private Hero myHero;
    private boolean myGameDone = false;
    private Random r = new Random();
    private DungeonAdventure myDungeonAdventure;

    private final AudioController audioController = new AudioController();

    private static final long serialVersionUID = 13425364675L;

    /**
     * Instantiates a new Console controller.
     *
     * @param theDungeonAdventure the dungeon adventure
     */
    public ConsoleController(DungeonAdventure theDungeonAdventure){
        myDungeonAdventure = theDungeonAdventure;
    }

    /**
     * Start game.
     *
     * @param theName  the name
     * @param theClass the class
     */
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
        myCurrRoom = myDungeon.getMyRoom();
        audioController.playBackgroundAudio();
    }

    /**
     * Build dungeon dungeon.
     *
     * @param theRows       the rows
     * @param theCols       the cols
     * @param theDifficulty the difficulty
     * @return the dungeon
     */
    public Dungeon buildDungeon(final int theRows, final int theCols, final String theDifficulty) {
        return new Dungeon(theRows, theCols, theDifficulty);
    }


    /**
     * Traverse dungeon.
     */
    public void traverseDungeon(){
        myCurrRoom = myDungeon.getMyRoom();
    }

    /**
     * Move north.
     */
    public void moveNorth(){
        myDungeon.moveHero(-1,0);
        traverseDungeon();
        System.out.println(myDungeon);
    }

    /**
     * Move south.
     */
    public void moveSouth(){
        myDungeon.moveHero(1,0);
        traverseDungeon();
        System.out.println(myDungeon);
    }

    /**
     * Move east.
     */
    public void moveEast(){
        myDungeon.moveHero(0,1);
        traverseDungeon();
        System.out.println(myDungeon);
    }

    /**
     * Move west.
     */
    public void moveWest(){
        myDungeon.moveHero(0,-1);
        traverseDungeon();
        System.out.println(myDungeon);
    }

    /**
     * Use heal potion.
     */
    public void useHealPotion(){
        int maxHealth = myHero.getMY_MAX_HEALTH();
        if(myHero.getMyHealingPotions()>0 && myHero.getMyHitPoints() < myHero.getMY_MAX_HEALTH()){
            int heal = r.nextInt((int) (.14*maxHealth),(int) (.28*maxHealth)); // can change later
            myHero.setMyHealingPotions(myHero.getMyHealingPotions()-1);
            myHero.setMyHitPoints(Math.min((myHero.getMyHitPoints() + heal), myHero.getMY_MAX_HEALTH()));
            System.out.println("You healed for " + heal + " health");
        }
        else if (myHero.getMyHealingPotions()>0 && myHero.getMyHitPoints() == myHero.getMY_MAX_HEALTH())
            System.out.println("You are already at full health");
        else System.out.println("No Healing Potions To Use");
    }

    /**
     * Use vision potion.
     */
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

    /**
     * Pit damage int.
     *
     * @return the int
     */
    public int pitDamage() {
        Random r = new Random();
        int damageRoll = r.nextInt(1, 15) + 1;
        return damageRoll;
    }

    /**
     * Gets my curr room.
     *
     * @return the my curr room
     */
    public Room getMyCurrRoom() {
        return myCurrRoom;
    }

    /**
     * Hero info.
     */
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

    /**
     * Hero inventory.
     */
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
            switch (input.toUpperCase()){
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
                    saveGame();
                    break;
                case "Q" :
                    System.exit(0);
                    break;
            }
        }
        System.out.println(myDungeon);
    }

    /**
     * Is alive boolean.
     *
     * @return the boolean
     */
    public boolean isAlive() {
        return myHero.getMyAlive();
    }

    /**
     * Traverse.
     */
    public void traverse() {
        Scanner scan = new Scanner(System.in);
        String dir;
        while (!myGameDone)   {
            System.out.println("Select an option to move the following:\n");
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
            switch (dir.toUpperCase()){
                case "N" :
                    if (myCurrRoom.getMyNorthRoom() != null) {
                        moveNorth();
                        checkRoom();
                    }
                    break;
                case "S" :
                    if (myCurrRoom.getMySouthRoom() != null) {
                        moveSouth();
                        checkRoom();
                    }
                    break;
                case "E":
                    if (myCurrRoom.getMyEastRoom() != null) {
                        moveEast();
                        checkRoom();
                    }
                    break;
                case "W":
                    if (myCurrRoom.getMyWestRoom() != null) {
                        moveWest();
                        checkRoom();
                    }
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

    /**
     * Check room.
     */
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
                audioController.playAudio("src/playerhurt2.wav");
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
                audioController.stopBackgroundAudio();
                audioController.playBattleAudio();
                Monster theMonster = room.getMyMonster();
                System.out.println("You have encountered a " + theMonster.getMyName() + "!");
                battle(myHero, theMonster);
                room.setMyMonster(null);
                room.setHasMonster(false);
                audioController.stopBattleAudio();
                if(myHero.getMyAlive()) {
                    System.out.println(myDungeon);
                    audioController.startBackgroundAudio();
                } else {
                    gameover();
                }
            }
            if(room.isExit()) {
                if(myHero.getMyPillars() < 4) {
                    System.out.println("You have not collected all the pillars!");
                }
                else {
                    audioController.stopBackgroundAudio();
                    System.out.println("You have collected all the pillars!");
                    pause(1500);
                    System.out.println("However, one last challenge stands in your way.");
                    pause(1500);
                    Monster theMonster = new MonsterFactory().createMonster("Lord of OO");
                    System.out.println("You have encountered a " + theMonster.getMyName() + "!");
                    pause(1500);
                    audioController.playBossAudio();
                    battle(myHero, theMonster);
                    myGameDone = true;
                    audioController.stopBossAudio();
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

    private void battle(final Hero theHero, final Monster theMonster) {
        Scanner scan = new Scanner(System.in);
        int myChoice;
        while(theHero.getMyAlive() && theMonster.getMyAlive()) {
            System.out.println(theHero.getMyName() + " health: " + theHero.getMyHitPoints());
            System.out.println(theMonster.getMyName() + " health: " + theMonster.getMyHitPoints());
            System.out.println("Attack: 1");
            System.out.println(myHero.getMySkillName() + ": 2");
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
                }
            }
        }
    }

    /**
     * Gets my dungeon.
     *
     * @return the dungeon
     */
    public Dungeon getMyDungeon() {
        return myDungeon;
    }

    /**
     * Sets my dungeon.
     *
     * @param theDungeon the dungeon
     */
    public void setMyDungeon(final Dungeon theDungeon) {
        this.myDungeon = theDungeon;
    }

    /**
     * Victory.
     */
    public void victory() {
        try(BufferedReader br = new BufferedReader(new FileReader("src/victory.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            audioController.playAudio("src/victorysound.wav");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gameover.
     */
    public void gameover() {
        try(BufferedReader br = new BufferedReader(new FileReader("src/gameover.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            audioController.playAudio("src/deathsound.wav");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Pause.
     *
     * @param theMs the ms
     */
    public void pause(int theMs) {
        try {
            Thread.sleep(theMs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Save game.
     */
    public void saveGame() {
        try {
            FileOutputStream fileOut = new FileOutputStream("src/savefile.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(myDungeonAdventure);
            out.close();
            fileOut.close();
            System.out.println("Your game has been saved");
        } catch (IOException i) {
            i.printStackTrace();
        }
        myDungeonAdventure = null;
    }

    /**
     * Load game.
     */
    public void loadGame() {
        try {
            FileInputStream fileIn = new FileInputStream("src/savefile.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            myDungeonAdventure = (DungeonAdventure) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Controller class not found");
            c.printStackTrace();
        }
        myDungeonAdventure.play();
    }
    private Object readResolve() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        audioController.setBackgroundClip();
        return this;
    }
}
