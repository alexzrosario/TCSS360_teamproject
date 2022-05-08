package main.DungeonMain;

import java.util.Random;

public class DungeonEXP {
    private Room[][] myDungeon;
    private Room myHeroPosition;
    //private Room myCurrentGeneratingRoom;
    private int myDungeonRows;
    private int myDungeonCols;
    private int myStartingRow;
    private int myStartingCol;
    private final Random r = new Random();
    /*private enum Directions{
        NORTH,
        SOUTH,
        EAST,
        WEST;
    }*/

    public DungeonEXP() {
        myDungeonRows = 5;
        myDungeonCols = 5;
        myDungeon = new Room[myDungeonRows][myDungeonCols];
        buildDungeonArray();
        generateDungeon();
        setAbstractionPillar();
        setEncapsulationPillar();
        setInheritancePillar();
        setPolymorphismPillar();
    }

    public void buildDungeonArray() {
        for(int row = 0; row < myDungeonRows; row++) {
            for(int col = 0; col < myDungeonCols; col++) {
                myDungeon[row][col] = new Room();
            }
        }
    }

    public void generateDungeon() {
        int roomsVisited = 1;
        setEntrance();
        int currentRow = myStartingRow;
        int currentCol = myStartingCol;
        int nextRow = myStartingRow;
        int nextCol = myStartingCol;
        myDungeon[currentRow][currentCol].setVisited();

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
                myDungeon[currentRow][currentCol].setMySouthRoom(myDungeon[nextRow][nextCol]);
                myDungeon[nextRow][nextCol].setMyNorthRoom(myDungeon[currentRow][currentCol]);
                currentRow = nextRow;
                //currentRoom.setMyNorthRoom(myDungeon[nextRow][nextCol]);
                if(!myDungeon[currentRow][currentCol].isVisited()) {
                    myDungeon[currentRow][currentCol].setVisited();
                    roomsVisited++;
                }
            }
            else if(currentRow - 1 > -1 && direction == 2) {
                nextRow = currentRow - 1;
                myDungeon[currentRow][currentCol].setMyNorthRoom(myDungeon[nextRow][nextCol]);
                myDungeon[nextRow][nextCol].setMySouthRoom(myDungeon[currentRow][currentCol]);
                currentRow = nextRow;
                //currentRoom.setMySouthRoom(myDungeon[nextRow][nextCol]);
                if(!myDungeon[currentRow][currentCol].isVisited()) {
                    myDungeon[currentRow][currentCol].setVisited();
                    roomsVisited++;
                }
            }
            else if(currentCol + 1 < myDungeonCols && direction == 3) {
                nextCol = currentCol + 1;
                myDungeon[currentRow][currentCol].setMyEastRoom(myDungeon[nextRow][nextCol]);
                myDungeon[nextRow][nextCol].setMyWestRoom(myDungeon[currentRow][currentCol]);
                currentCol = nextCol;
                //currentRoom.setMyEastRoom(myDungeon[nextRow][nextCol]);
                if(!myDungeon[currentRow][currentCol].isVisited()) {
                    myDungeon[currentRow][currentCol].setVisited();
                    roomsVisited++;
                }
            }
            else if(currentCol - 1 > -1 && direction == 4) {
                nextCol = currentCol - 1;
                myDungeon[currentRow][currentCol].setMyWestRoom(myDungeon[nextRow][nextCol]);
                myDungeon[nextRow][nextCol].setMyEastRoom(myDungeon[currentRow][currentCol]);
                currentCol = nextCol;
                //currentRoom.setMyWestRoom(myDungeon[nextRow][nextCol]);
                //currentRoom = myDungeon[nextRow][nextCol];
                if(!myDungeon[currentRow][currentCol].isVisited()) {
                    myDungeon[currentRow][currentCol].setVisited();
                    roomsVisited++;
                }
            }
        }
        setExit(currentRow, currentCol);
    }

    public void setEntrance() {
        int row = r.nextInt(myDungeonRows);
        int col = r.nextInt(myDungeonCols);
        myStartingRow = row;
        myStartingCol = col;
        myDungeon[row][col].setEntrance(true);
        myDungeon[row][col].setHasHealPotion(false);
        myDungeon[row][col].setHasVisionPotion(false);
        myDungeon[row][col].setHasPit(false);
        myDungeon[row][col].setMyStringToken("i");
    }

    public void setExit(int row, int col) {
        myDungeon[row][col].setExit(true);
        myDungeon[row][col].setHasHealPotion(false);
        myDungeon[row][col].setHasVisionPotion(false);
        myDungeon[row][col].setHasPit(false);
        myDungeon[row][col].setMyStringToken("O");
    }

    public void setAbstractionPillar() {
        int row = r.nextInt(myDungeonRows);
        int col = r.nextInt(myDungeonCols);
        if(myDungeon[row][col].isEntrance() || myDungeon[row][col].isExit() ||
                myDungeon[row][col].isHasEncapsulationPillar() || myDungeon[row][col].isHasInheritancePillar() ||
                myDungeon[row][col].isHasPolymorphismPillar()) {
            setAbstractionPillar();
        } else {
            myDungeon[row][col].setHasAbstractionPillar(true);
            myDungeon[row][col].setMyStringToken("A");
        }
    }

    public void setInheritancePillar() {
        int row = r.nextInt(myDungeonRows);
        int col = r.nextInt(myDungeonCols);
        if(myDungeon[row][col].isEntrance() || myDungeon[row][col].isExit() ||
                myDungeon[row][col].isHasEncapsulationPillar() || myDungeon[row][col].isHasAbstractionPillar() ||
                myDungeon[row][col].isHasPolymorphismPillar()) {
            setInheritancePillar();
        } else {
            myDungeon[row][col].setHasInheritancePillar(true);
            myDungeon[row][col].setMyStringToken("I");
        }
    }

    public void setEncapsulationPillar() {
        int row = r.nextInt(myDungeonRows);
        int col = r.nextInt(myDungeonCols);
        if(myDungeon[row][col].isEntrance() || myDungeon[row][col].isExit() ||
                myDungeon[row][col].isHasAbstractionPillar() || myDungeon[row][col].isHasInheritancePillar() ||
                myDungeon[row][col].isHasPolymorphismPillar()) {
            setEncapsulationPillar();
        } else {
            myDungeon[row][col].setHasEncapsulationPillar(true);
            myDungeon[row][col].setMyStringToken("E");
        }
    }

    public void setPolymorphismPillar() {
        int row = r.nextInt(myDungeonRows);
        int col = r.nextInt(myDungeonCols);
        if(myDungeon[row][col].isEntrance() || myDungeon[row][col].isExit() ||
                myDungeon[row][col].isHasEncapsulationPillar() || myDungeon[row][col].isHasInheritancePillar() ||
                myDungeon[row][col].isHasAbstractionPillar()) {
            setPolymorphismPillar();
        } else {
            myDungeon[row][col].setHasPolymorphismPillar(true);
            myDungeon[row][col].setMyStringToken("P");
        }
    }

    public String toString() {
        String s = "";
        for(int row = 0; row < myDungeonRows; row++) {
            for(int col = 0; col < myDungeonCols; col++) {
                //s += "Row: " + row + " --- Col: " + col + "\n";
                s += myDungeon[row][col].toString();
            }
            s += "\n";
        }
        return s;
    }

}

