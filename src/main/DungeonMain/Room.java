package main.DungeonMain;

import main.DungeonCharacter.Monster;
import main.DungeonCharacter.MonsterFactory;

import java.util.Random;

public class Room {
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
    private static String[] monsterArray = {"ogre", "gremlin", "skeleton"};

    public Room() {
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
        // Determining if the room contains a monster, and randomly decides which monster to use
        if (Math.random() < 0.25) {
            int monsterRoll = theRandom.nextInt(3);
            setMyMonster(new MonsterFactory().createMonster(monsterArray[monsterRoll]));
            setHasMonster(true);

            myStringToken = "m"; // temp string token to indicate if a monster exists in the room
        }
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

    public Room getMyNorthRoom() {
        return myNorthRoom;
    }

    public void setMyNorthRoom(Room myNorthRoom) {
        this.myNorthRoom = myNorthRoom;
    }

    public Room getMyEastRoom() {
        return myEastRoom;
    }

    public void setMyEastRoom(Room myEastRoom) {
        this.myEastRoom = myEastRoom;
    }

    public Room getMySouthRoom() {
        return mySouthRoom;
    }

    public void setMySouthRoom(Room mySouthRoom) {
        this.mySouthRoom = mySouthRoom;
    }

    public Room getMyWestRoom() {
        return myWestRoom;
    }

    public void setMyWestRoom(Room myWestRoom) {
        this.myWestRoom = myWestRoom;
    }

    public boolean isHasPit() {
        return hasPit;
    }

    public void setHasPit(boolean hasPit) {
        this.hasPit = hasPit;
    }

    public boolean isHasVisionPotion() {
        return hasVisionPotion;
    }

    public void setHasVisionPotion(boolean hasVisionPotion) {
        this.hasVisionPotion = hasVisionPotion;
    }

    public boolean isHasHealPotion() {
        return hasHealPotion;
    }

    public void setHasHealPotion(boolean hasHealPotion) {
        this.hasHealPotion = hasHealPotion;
    }

    public boolean isHasAbstractionPillar() {
        return hasAbstractionPillar;
    }

    public void setHasAbstractionPillar(boolean hasAbstractionPillar) {
        this.hasAbstractionPillar = hasAbstractionPillar;
    }

    public boolean isHasEncapsulationPillar() {
        return hasEncapsulationPillar;
    }

    public void setHasEncapsulationPillar(boolean hasEncapsulationPillar) {
        this.hasEncapsulationPillar = hasEncapsulationPillar;
    }

    public boolean isHasInheritancePillar() {
        return hasInheritancePillar;
    }

    public void setHasInheritancePillar(boolean hasInheritancePillar) {
        this.hasInheritancePillar = hasInheritancePillar;
    }

    public boolean isHasPolymorphismPillar() {
        return hasPolymorphismPillar;
    }

    public void setHasPolymorphismPillar(boolean hasPolymorphismPillar) {
        this.hasPolymorphismPillar = hasPolymorphismPillar;
    }

    public boolean isEntrance() {
        return isEntrance;
    }

    public void setEntrance(boolean entrance) {
        isEntrance = entrance;
    }

    public boolean isExit() {
        return isExit;
    }

    public void setExit(boolean exit) {
        isExit = exit;
    }

    public String getMyStringToken() {
        return myStringToken;
    }

    public void setMyStringToken(String myStringToken) {
        this.myStringToken = myStringToken;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited() {
        isVisited = true;
    }

    public boolean isBuilt() {
        return isBuilt;
    }

    public void setBuilt() {
        isBuilt = true;
    }

    public boolean isHasMonster() {
        return hasMonster;
    }

    public void setHasMonster(boolean hasMonster) {
        this.hasMonster = hasMonster;
    }

    public Monster getMyMonster() {
        return myMonster;
    }

    public void setMyMonster(Monster myMonster) {
        this.myMonster = myMonster;
    }

    public boolean isSeen() {
        return isSeen;
    }

    public void setSeen(boolean seen) {
        isSeen = seen;
    }
}