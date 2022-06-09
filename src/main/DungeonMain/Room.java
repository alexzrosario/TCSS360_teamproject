package main.DungeonMain;

import main.DungeonCharacter.Monster;
import main.DungeonCharacter.MonsterFactory;

import java.io.Serializable;
import java.util.Random;

/**
 * The type Room.
 */
public class Room implements Serializable {
    private Room myNorthRoom;
    private Room myEastRoom;
    private Room mySouthRoom;
    private Room myWestRoom;
    private boolean hasPit = false;
    private boolean hasVisionPotion = false;
    private boolean hasHealPotion = false;
    private boolean hasAbstractionPillar = false;
    private boolean hasEncapsulationPillar = false;
    private boolean hasInheritancePillar = false;
    private boolean hasPolymorphismPillar = false;
    private boolean isEntrance = false;
    private boolean isExit = false;
    private boolean isVisited = false;
    private boolean isBuilt = false;
    private boolean hasMonster = false;
    private boolean isSeen = false;
    private Monster myMonster;
    private String myStringToken = " ";
    private static final String[] monsterArray = {"ogre", "gremlin", "skeleton"};
    private double myDifficultyModifier;
    private static final long serialVersionUID = 3536060713340084481L;

    /**
     * Instantiates a new Room.
     *
     * @param theDifficulty the difficulty
     */
    public Room(final String theDifficulty) {
        Random theRandom = new Random();
        int items = 0;
        int itemRoll = theRandom.nextInt(10) + 1; // Determining if the room will contain a pit
        if (itemRoll == 1) {
            hasPit = true;
            items++;
            myStringToken = "X";
        }
        itemRoll = theRandom.nextInt(10) + 1; // Determining if the room will contain a vision potion
        if (itemRoll == 1) {
            hasVisionPotion = true;
            items++;
            myStringToken = "V";
        }
        itemRoll = theRandom.nextInt(10) + 1; // Determining if the room will contain a healing potion
        if (itemRoll == 1) {
            hasHealPotion = true;
            items++;
            myStringToken = "H";
        }
        if (items > 1) {
            myStringToken = "M";
        }
        findDifficultyModifier(theDifficulty);
        // Determining if the room contains a monster, and randomly decides which monster to use
        if (Math.random() < myDifficultyModifier) {
            int monsterRoll = theRandom.nextInt(3);
            setMyMonster(new MonsterFactory().createMonster(monsterArray[monsterRoll]));
            setHasMonster(true);

            myStringToken = "m"; // temp string token to indicate if a monster exists in the room
        }
    }

    /**
     * Find difficulty modifier.
     *
     * @param theDifficulty the difficulty
     */
    public void findDifficultyModifier(final String theDifficulty) {
        if (theDifficulty.equals("EASY")) myDifficultyModifier = 0.125;
        if (theDifficulty.equals("NORMAL")) myDifficultyModifier = 0.25;
        if (theDifficulty.equals("HARD")) myDifficultyModifier = 0.3333333;
    }

    public String toString() {
        String roomString = "";
        if (myNorthRoom != null) roomString += "*-*\n";
        else roomString += "***\n";

        if (myWestRoom != null) roomString += "|";
        else roomString += "*";

        roomString += myStringToken;

        if (myEastRoom != null) roomString += "|\n";
        else roomString += "*\n";

        if (mySouthRoom != null) roomString += "*-*\n";
        else roomString += "***\n";

        return roomString;
    }

    /**
     * Gets my north room.
     *
     * @return the north room
     */
    public Room getMyNorthRoom() {
        return myNorthRoom;
    }

    /**
     * Sets my north room.
     *
     * @param theNorthRoom the north room
     */
    public void setMyNorthRoom(final Room theNorthRoom) {
        this.myNorthRoom = theNorthRoom;
    }

    /**
     * Gets my east room.
     *
     * @return the east room
     */
    public Room getMyEastRoom() {
        return myEastRoom;
    }

    /**
     * Sets my east room.
     *
     * @param theEastRoom the east room
     */
    public void setMyEastRoom(final Room theEastRoom) {
        this.myEastRoom = theEastRoom;
    }

    /**
     * Gets my south room.
     *
     * @return the south room
     */
    public Room getMySouthRoom() {
        return mySouthRoom;
    }

    /**
     * Sets my south room.
     *
     * @param theSouthRoom the south room
     */
    public void setMySouthRoom(final Room theSouthRoom) {
        this.mySouthRoom = theSouthRoom;
    }

    /**
     * Gets my west room.
     *
     * @return the west room
     */
    public Room getMyWestRoom() {
        return myWestRoom;
    }

    /**
     * Sets my west room.
     *
     * @param theWestRoom the west room
     */
    public void setMyWestRoom(final Room theWestRoom) {
        this.myWestRoom = theWestRoom;
    }

    /**
     * Is has pit boolean.
     *
     * @return the boolean
     */
    public boolean isHasPit() {
        return hasPit;
    }

    /**
     * Sets has pit.
     *
     * @param hasPit the has pit
     */
    public void setHasPit(boolean hasPit) {
        this.hasPit = hasPit;
    }

    /**
     * Is has vision potion boolean.
     *
     * @return the boolean
     */
    public boolean isHasVisionPotion() {
        return hasVisionPotion;
    }

    /**
     * Sets has vision potion.
     *
     * @param hasVisionPotion the has vision potion
     */
    public void setHasVisionPotion(boolean hasVisionPotion) {
        this.hasVisionPotion = hasVisionPotion;
    }

    /**
     * Is has heal potion boolean.
     *
     * @return the boolean
     */
    public boolean isHasHealPotion() {
        return hasHealPotion;
    }

    /**
     * Sets has heal potion.
     *
     * @param hasHealPotion the has heal potion
     */
    public void setHasHealPotion(boolean hasHealPotion) {
        this.hasHealPotion = hasHealPotion;
    }

    /**
     * Is has abstraction pillar boolean.
     *
     * @return the boolean
     */
    public boolean isHasAbstractionPillar() {
        return hasAbstractionPillar;
    }

    /**
     * Sets has abstraction pillar.
     *
     * @param hasAbstractionPillar the has abstraction pillar
     */
    public void setHasAbstractionPillar(boolean hasAbstractionPillar) {
        this.hasAbstractionPillar = hasAbstractionPillar;
    }

    /**
     * Is has encapsulation pillar boolean.
     *
     * @return the boolean
     */
    public boolean isHasEncapsulationPillar() {
        return hasEncapsulationPillar;
    }

    /**
     * Sets has encapsulation pillar.
     *
     * @param hasEncapsulationPillar the has encapsulation pillar
     */
    public void setHasEncapsulationPillar(boolean hasEncapsulationPillar) {
        this.hasEncapsulationPillar = hasEncapsulationPillar;
    }

    /**
     * Is has inheritance pillar boolean.
     *
     * @return the boolean
     */
    public boolean isHasInheritancePillar() {
        return hasInheritancePillar;
    }

    /**
     * Sets has inheritance pillar.
     *
     * @param hasInheritancePillar the has inheritance pillar
     */
    public void setHasInheritancePillar(boolean hasInheritancePillar) {
        this.hasInheritancePillar = hasInheritancePillar;
    }

    /**
     * Is has polymorphism pillar boolean.
     *
     * @return the boolean
     */
    public boolean isHasPolymorphismPillar() {
        return hasPolymorphismPillar;
    }

    /**
     * Sets has polymorphism pillar.
     *
     * @param hasPolymorphismPillar the has polymorphism pillar
     */
    public void setHasPolymorphismPillar(boolean hasPolymorphismPillar) {
        this.hasPolymorphismPillar = hasPolymorphismPillar;
    }

    /**
     * Is entrance boolean.
     *
     * @return the boolean
     */
    public boolean isEntrance() {
        return isEntrance;
    }

    /**
     * Sets entrance.
     *
     * @param entrance the entrance
     */
    public void setEntrance(boolean entrance) {
        isEntrance = entrance;
    }

    /**
     * Is exit boolean.
     *
     * @return the boolean
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Sets exit.
     *
     * @param exit the exit
     */
    public void setExit(boolean exit) {
        isExit = exit;
    }

    /**
     * Gets my string token.
     *
     * @return the my string token
     */
    public String getMyStringToken() {
        return myStringToken;
    }

    /**
     * Sets my string token.
     *
     * @param myStringToken the my string token
     */
    public void setMyStringToken(String myStringToken) {
        this.myStringToken = myStringToken;
    }

    /**
     * Is visited boolean.
     *
     * @return the boolean
     */
    public boolean isVisited() {
        return isVisited;
    }

    /**
     * Sets visited.
     */
    public void setVisited() {
        isVisited = true;
    }

    /**
     * Is built boolean.
     *
     * @return the boolean
     */
    public boolean isBuilt() {
        return isBuilt;
    }

    /**
     * Sets built.
     */
    public void setBuilt() {
        isBuilt = true;
    }

    /**
     * Is has monster boolean.
     *
     * @return the boolean
     */
    public boolean isHasMonster() {
        return hasMonster;
    }

    /**
     * Sets has monster.
     *
     * @param hasMonster the has monster
     */
    public void setHasMonster(boolean hasMonster) {
        this.hasMonster = hasMonster;
    }

    /**
     * Gets my monster.
     *
     * @return the monster
     */
    public Monster getMyMonster() {
        return myMonster;
    }

    /**
     * Sets my monster.
     *
     * @param theMonster the monster
     */
    public void setMyMonster(final Monster theMonster) {
        this.myMonster = theMonster;
    }

    /**
     * Is seen boolean.
     *
     * @return the boolean
     */
    public boolean isSeen() {
        return isSeen;
    }

    /**
     * Sets seen.
     *
     * @param seen the seen
     */
    public void setSeen(boolean seen) {
        isSeen = seen;
    }

    /**
     * Gets my difficulty modifier.
     *
     * @return the difficulty modifier
     */
    public double getMyDifficultyModifier() {
        return myDifficultyModifier;
    }

    /**
     * Sets my difficulty modifier.
     *
     * @param myDifficultyModifier the difficulty modifier
     */
    public void setMyDifficultyModifier(final double myDifficultyModifier) {
        this.myDifficultyModifier = myDifficultyModifier;
    }
}