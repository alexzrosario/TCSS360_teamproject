package main.DungeonMain;

import java.util.Random;

public class Dungeon {
    private Room[][] dungeon;
    private Room heroPosition;
    private int startingRow;
    private int startingCol;

    public Dungeon() {
        dungeon = new Room[5][5];
    }

    public void generateDungeon() {
        for(int row = 0; row < dungeon.length; row++) {
            for(int col = 0; col < dungeon.length; col++) {
                dungeon[row][col] = new Room();
            }
        }
    }

    public void setEntrance() {
        Random r = new Random();
        int row = r.nextInt(5);
        int col = r.nextInt(5);
        startingRow = row;
        startingCol = col;
        dungeon[row][col].setEntrance(true);
        dungeon[row][col].setHasHealPotion(false);
        dungeon[row][col].setHasVisionPotion(false);
        dungeon[row][col].setHasPit(false);
    }

    public void setHeroStart() {
        heroPosition = dungeon[startingRow][startingCol];
    }

    public void setExit() {
        Random r = new Random();
        int row = r.nextInt(5);
        int col = r.nextInt(5);
        if (dungeon[row][col] == dungeon[startingRow][startingCol]) {
            setExit();
        } else {
            dungeon[row][col].setExit(true);
            dungeon[row][col].setHasHealPotion(false);
            dungeon[row][col].setHasVisionPotion(false);
            dungeon[row][col].setHasPit(false);
        }
    }

    public void setAbstractionPillar() {
        Random r = new Random();
        int row = r.nextInt(5);
        int col = r.nextInt(5);
        if(dungeon[row][col].isEntrance() || dungeon[row][col].isExit() ||
                dungeon[row][col].isHasEncapsulationPillar() || dungeon[row][col].isHasInheritancePillar() ||
                dungeon[row][col].isHasPolymorphismPillar()) {
            setAbstractionPillar();
        } else {
            dungeon[row][col].setHasAbstractionPillar(true);
        }
    }

    public void setInheritancePillar() {
        Random r = new Random();
        int row = r.nextInt(5);
        int col = r.nextInt(5);
        if(dungeon[row][col].isEntrance() || dungeon[row][col].isExit() ||
                dungeon[row][col].isHasEncapsulationPillar() || dungeon[row][col].isHasAbstractionPillar() ||
                dungeon[row][col].isHasPolymorphismPillar()) {
            setInheritancePillar();
        } else {
            dungeon[row][col].setHasInheritancePillar(true);
        }
    }

    public void setEncapsulationPillar() {
        Random r = new Random();
        int row = r.nextInt(5);
        int col = r.nextInt(5);
        if(dungeon[row][col].isEntrance() || dungeon[row][col].isExit() ||
                dungeon[row][col].isHasAbstractionPillar() || dungeon[row][col].isHasInheritancePillar() ||
                dungeon[row][col].isHasPolymorphismPillar()) {
            setEncapsulationPillar();
        } else {
            dungeon[row][col].setHasEncapsulationPillar(true);
        }
    }

    public void setPolymorphismPillar() {
        Random r = new Random();
        int row = r.nextInt(5);
        int col = r.nextInt(5);
        if(dungeon[row][col].isEntrance() || dungeon[row][col].isExit() ||
                dungeon[row][col].isHasEncapsulationPillar() || dungeon[row][col].isHasInheritancePillar() ||
                dungeon[row][col].isHasAbstractionPillar()) {
            setPolymorphismPillar();
        } else {
            dungeon[row][col].setHasPolymorphismPillar(true);
        }
    }

    public void setMonsters() {

    }

    public String toString() {
        return "";
    }
}