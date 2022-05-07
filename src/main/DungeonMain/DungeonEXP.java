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
    }

    public void buildDungeonArray() {
        for(int row = 0; row < myDungeonRows; row++) {
            for(int col = 0; col < myDungeonCols; col++) {
                myDungeon[row][col] = new Room();
            }
        }
    }

    public void generateDungeon() {
        Random r = new Random();
        int roomsVisited = 1;
        setEntrance();
        int currentRow = myStartingRow;
        int currentCol = myStartingCol;
        int nextRow = myStartingRow;
        int nextCol = myStartingCol;
        Room currentRoom = myDungeon[currentRow][currentCol];

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
                myDungeon[currentRow][currentCol].setMyWestRoom(myDungeon[nextRow][nextCol]);
                currentRow = nextRow;
                //currentRoom.setMyNorthRoom(myDungeon[nextRow][nextCol]);
                if(!myDungeon[currentRow][currentCol].isVisited()) roomsVisited++;
            }
            else if(currentRow - 1 > -1 && direction == 2) {
                nextRow = currentRow - 1;
                myDungeon[currentRow][currentCol].setMyWestRoom(myDungeon[nextRow][nextCol]);
                currentRow = nextRow;
                //currentRoom.setMySouthRoom(myDungeon[nextRow][nextCol]);
                if(!myDungeon[currentRow][currentCol].isVisited()) roomsVisited++;
            }
            else if(currentCol + 1 < myDungeonCols && direction == 3) {
                nextCol = currentCol + 1;
                myDungeon[currentRow][currentCol].setMyWestRoom(myDungeon[nextRow][nextCol]);
                currentCol = nextCol;
                //currentRoom.setMyEastRoom(myDungeon[nextRow][nextCol]);
                if(!myDungeon[currentRow][currentCol].isVisited()) roomsVisited++;
            }
            else if(currentCol - 1 > -1 && direction == 4) {
                nextCol = currentCol - 1;
                myDungeon[currentRow][currentCol].setMyWestRoom(myDungeon[nextRow][nextCol]);
                currentCol = nextCol;
                //currentRoom.setMyWestRoom(myDungeon[nextRow][nextCol]);
                //currentRoom = myDungeon[nextRow][nextCol];
                if(!myDungeon[currentRow][currentCol].isVisited()) roomsVisited++;
            }
        }
        setExit(currentRow, currentCol);
    }

    public void setEntrance() {
        Random r = new Random();
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

    public String toString() {
        String s = "";
        for(int row = 0; row < myDungeonRows; row++) {
            for(int col = 0; col < myDungeonCols; col++) {
                s += myDungeon[row][col].toString();
            }
            s += "\n";
        }
        return s;
    }

}

