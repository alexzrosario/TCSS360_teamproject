package main.DungeonMain;

import java.util.Random;
public class Dungeon {
    private Room[][] dungeon;
    private Room heroPosition;
    private int startingRow;
    private int startingCol;
    private int pillarCount = 4;

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
        dungeon[row][col].isEntrance = true;
        dungeon[row][col].hasHealPotion = false;
        dungeon[row][col].hasVisionPotion = false;
        dungeon[row][col].hasPit = false;
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
            dungeon[row][col].isExit = true;
            dungeon[row][col].hasHealPotion = false;
            dungeon[row][col].hasVisionPotion = false;
            dungeon[row][col].hasPit = false;
        }
    }

    public void setPillars() {
        Random r = new Random();
        while(pillarCount > 0) {
            int row = r.nextInt(5);
            int col = r.nextInt(5);
            if(dungeon[row][col].hasPillar || dungeon[row][col].isEntrance || dungeon[row][col].isExit) {
                setPillars();
            } else {
                dungeon[row][col].hasPillar = true;
            }
            pillarCount--;
        }
    }

    public String toString() {
        return "";
    }
}
