package main.DungeonMain;

import java.util.Random;

public class Dungeon {
    private Room[][] dungeon;
    private Room heroPosition;
    private int startingRow;
    private int startingCol;
    private int myDungeonRows;
    private int myDungeonCols;

    public Dungeon() {
        myDungeonRows = 5;
        myDungeonCols = 5;
        dungeon = new Room[myDungeonRows][myDungeonCols];
        buildDungeonArray();
        generateDungeon();
    }

    public void buildDungeonArray() {
        for(int row = 0; row < dungeon.length; row++) {
            for(int col = 0; col < dungeon.length; col++) {
                dungeon[row][col] = new Room();
            }
        }
    }
    public void generateDungeon() {
        Random r = new Random();
        int roomsVisited = 1;
        setEntrance();
        int currentRow = startingRow;
        int currentCol = startingCol;
        int nextRow = startingRow;
        int nextCol = startingCol;
        dungeon[currentRow][currentCol].setVisited();

        while (roomsVisited < myDungeonRows*myDungeonCols) {
            //Temporary
            //Change later
            int direction = r.nextInt(4) + 1;
            //1 - NORTH
            //2 - SOUTH
            //3 - EAST
            //4 - WEST
            if(currentRow + 1 < myDungeonRows && direction == 1) {
                nextRow = currentRow + 1;
                dungeon[currentRow][currentCol].setMyNorthRoom(dungeon[nextRow][nextCol]);
                currentRow = nextRow;
                //currentRoom.setMyNorthRoom(myDungeon[nextRow][nextCol]);
                if(!dungeon[currentRow][currentCol].isVisited()) {
                    dungeon[currentRow][currentCol].setVisited();
                    roomsVisited++;
                }
            }
            else if(currentRow - 1 > -1 && direction == 2) {
                nextRow = currentRow - 1;
                dungeon[currentRow][currentCol].setMySouthRoom(dungeon[nextRow][nextCol]);
                currentRow = nextRow;
                //currentRoom.setMySouthRoom(myDungeon[nextRow][nextCol]);
                if(!dungeon[currentRow][currentCol].isVisited()) {
                    dungeon[currentRow][currentCol].setVisited();
                    roomsVisited++;
                }
            }
            else if(currentCol + 1 < myDungeonCols && direction == 3) {
                nextCol = currentCol + 1;
                dungeon[currentRow][currentCol].setMyEastRoom(dungeon[nextRow][nextCol]);
                currentCol = nextCol;
                //currentRoom.setMyEastRoom(myDungeon[nextRow][nextCol]);
                if(!dungeon[currentRow][currentCol].isVisited()) {
                    dungeon[currentRow][currentCol].setVisited();
                    roomsVisited++;
                }
            }
            else if(currentCol - 1 > -1 && direction == 4) {
                nextCol = currentCol - 1;
                dungeon[currentRow][currentCol].setMyWestRoom(dungeon[nextRow][nextCol]);
                currentCol = nextCol;
                //currentRoom.setMyWestRoom(myDungeon[nextRow][nextCol]);
                //currentRoom = myDungeon[nextRow][nextCol];
                if(!dungeon[currentRow][currentCol].isVisited()) {
                    dungeon[currentRow][currentCol].setVisited();
                    roomsVisited++;
                }
            }
        }
        setExit();
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
        dungeon[row][col].setMyStringToken("i");
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
            dungeon[row][col].setMyStringToken("O");
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
            dungeon[row][col].setMyStringToken("A");
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
            dungeon[row][col].setMyStringToken("I");
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
            dungeon[row][col].setMyStringToken("E");
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
            dungeon[row][col].setMyStringToken("P");
        }
    }

    public void setMonsters() {

    }

    public String toString() {
        StringBuilder s = new StringBuilder();

        for(int i = 0; i < dungeon.length; i++) {
            s.append("\n");
            for(int j = 0; j < dungeon[i].length; j++) {
                s.append(dungeon[i][j].toString());
            }
        }
        return s.toString();
    }
}