package main.DungeonMain;

import java.util.Random;

public class Dungeon {
    private Room[][] myDungeon;
    //private Room myHeroPosition;
    private int myHeroRow;
    private int myHeroCol;
    private int myDungeonRows;
    private int myDungeonCols;
    private int myStartingRow;
    private int myStartingCol;
    private final Random r = new Random();

    public Dungeon(int theDungeonRows, int theDungeonCols) {
        myDungeonRows = theDungeonRows;
        myDungeonCols = theDungeonCols;
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
        int roomsBuilt = 1;
        myStartingRow = r.nextInt(myDungeonRows);
        myStartingCol = r.nextInt(myDungeonCols);
        myHeroRow = myStartingRow;
        myHeroCol = myStartingCol;
        //myHeroPosition = myDungeon[myStartingRow][myStartingCol];
        setEntrance(myStartingRow, myStartingCol);
        int currentRow = myStartingRow;
        int currentCol = myStartingCol;
        int nextRow = myStartingRow;
        int nextCol = myStartingCol;
        myDungeon[currentRow][currentCol].setBuilt();
        myDungeon[currentRow][currentCol].setVisited();
        String[] directions = {"NORTH", "SOUTH", "EAST", "WEST"};

        while (roomsBuilt < myDungeonRows*myDungeonCols) {
            String direction = directions[r.nextInt(4)];
            if(currentRow < myDungeonRows - 1 && direction.equals("SOUTH")) {
                nextRow = currentRow + 1;
                myDungeon[currentRow][currentCol].setMySouthRoom(myDungeon[nextRow][nextCol]);
                myDungeon[nextRow][nextCol].setMyNorthRoom(myDungeon[currentRow][currentCol]);
                currentRow = nextRow;
                roomsBuilt += checkBuilt(myDungeon[currentRow][currentCol]);
            }
            else if(currentRow > 0 && direction.equals("NORTH")) {
                nextRow = currentRow - 1;
                myDungeon[currentRow][currentCol].setMyNorthRoom(myDungeon[nextRow][nextCol]);
                myDungeon[nextRow][nextCol].setMySouthRoom(myDungeon[currentRow][currentCol]);
                currentRow = nextRow;
                roomsBuilt += checkBuilt(myDungeon[currentRow][currentCol]);
            }
            else if(currentCol < myDungeonCols - 1 && direction.equals("EAST")) {
                nextCol = currentCol + 1;
                myDungeon[currentRow][currentCol].setMyEastRoom(myDungeon[nextRow][nextCol]);
                myDungeon[nextRow][nextCol].setMyWestRoom(myDungeon[currentRow][currentCol]);
                currentCol = nextCol;
                roomsBuilt += checkBuilt(myDungeon[currentRow][currentCol]);
            }
            else if(currentCol > 0 && direction.equals("WEST")) {
                nextCol = currentCol - 1;
                myDungeon[currentRow][currentCol].setMyWestRoom(myDungeon[nextRow][nextCol]);
                myDungeon[nextRow][nextCol].setMyEastRoom(myDungeon[currentRow][currentCol]);
                currentCol = nextCol;
                roomsBuilt += checkBuilt(myDungeon[currentRow][currentCol]);
            }
        }
        setExit(currentRow, currentCol);
        setAbstractionPillar(r.nextInt(myDungeonRows), r.nextInt(myDungeonCols));
        setEncapsulationPillar(r.nextInt(myDungeonRows), r.nextInt(myDungeonCols));
        setInheritancePillar(r.nextInt(myDungeonRows), r.nextInt(myDungeonCols));
        setPolymorphismPillar(r.nextInt(myDungeonRows), r.nextInt(myDungeonCols));
    }

    public void setEntrance(int row, int col) {
        myDungeon[row][col].setEntrance(true);
        disableItems(row, col);
        myDungeon[row][col].setMyStringToken("i");
    }

    public void setExit(int row, int col) {
        myDungeon[row][col].setExit(true);
        disableItems(row, col);
        myDungeon[row][col].setMyStringToken("O");
    }

    public void setAbstractionPillar(int row, int col) {
        if(myDungeon[row][col].isEntrance() || myDungeon[row][col].isExit() ||
                myDungeon[row][col].isHasEncapsulationPillar() || myDungeon[row][col].isHasInheritancePillar() ||
                myDungeon[row][col].isHasPolymorphismPillar()) {
            setAbstractionPillar(r.nextInt(myDungeonRows), r.nextInt(myDungeonCols));
        } else {
            myDungeon[row][col].setHasAbstractionPillar(true);
            myDungeon[row][col].setMyStringToken("A");
        }
    }

    public void setInheritancePillar(int row, int col) {
        if(myDungeon[row][col].isEntrance() || myDungeon[row][col].isExit() ||
                myDungeon[row][col].isHasEncapsulationPillar() || myDungeon[row][col].isHasAbstractionPillar() ||
                myDungeon[row][col].isHasPolymorphismPillar()) {
            setInheritancePillar(r.nextInt(myDungeonRows), r.nextInt(myDungeonCols));
        } else {
            myDungeon[row][col].setHasInheritancePillar(true);
            myDungeon[row][col].setMyStringToken("I");
        }
    }

    public void setEncapsulationPillar(int row, int col) {
        if(myDungeon[row][col].isEntrance() || myDungeon[row][col].isExit() ||
                myDungeon[row][col].isHasAbstractionPillar() || myDungeon[row][col].isHasInheritancePillar() ||
                myDungeon[row][col].isHasPolymorphismPillar()) {
            setEncapsulationPillar(r.nextInt(myDungeonRows), r.nextInt(myDungeonCols));
        } else {
            myDungeon[row][col].setHasEncapsulationPillar(true);
            myDungeon[row][col].setMyStringToken("E");
        }
    }

    public void setPolymorphismPillar(int row, int col) {
        if(myDungeon[row][col].isEntrance() || myDungeon[row][col].isExit() ||
                myDungeon[row][col].isHasEncapsulationPillar() || myDungeon[row][col].isHasInheritancePillar() ||
                myDungeon[row][col].isHasAbstractionPillar()) {
            setPolymorphismPillar(r.nextInt(myDungeonRows), r.nextInt(myDungeonCols));
        } else {
            myDungeon[row][col].setHasPolymorphismPillar(true);
            myDungeon[row][col].setMyStringToken("P");
        }
    }

    public int checkBuilt(Room theRoom) {
        if (!theRoom.isBuilt()) {
            theRoom.setBuilt();
            return 1;
        }
        else return 0;
    }

    public void disableItems(int row, int col) {
        myDungeon[row][col].setHasHealPotion(false);
        myDungeon[row][col].setHasVisionPotion(false);
        myDungeon[row][col].setHasPit(false);
    }

    public void moveHero(int rowDirection, int colDirection) {
        myDungeon[myHeroRow][myHeroCol].setMyStringToken("v");
        myHeroRow += rowDirection;
        myHeroCol += colDirection;
        myDungeon[myHeroRow][myHeroCol].setMyStringToken("h");
    }

    public String toString() {
        String s = "";
        for(int row = 0; row < myDungeonRows; row++) {
            s += getTopString(row);
            s += getEmptyMidString(row);
            //s += getMidString(row);
        }
        s+= "***********";
        return s;
    }
    public String getTopString(final int theRow){
        String s = "*";
        for (int i = 0; i < myDungeonRows; i++) {
            if(myDungeon[theRow][i].getMyNorthRoom() == null){
                s += "**";
            } else{
                s += "-*";
            }
        }
        s += "\n";
        return s;
    }
    public String getEmptyMidString(final int theRow){
        String s = "*";
        for (int i = 0; i < myDungeonRows; i++) {
            //s += myDungeon[theRow][i].getMyStringToken(); show all tokens
            if (myDungeon[theRow][i].getMyStringToken() == "i") {
                s += "h";
            }else if(myDungeon[theRow][i].getMyStringToken()== "h") {
                s += "h";
            }else if(myDungeon[theRow][i].getMyStringToken()== "v") {
                s += "v";
            }else {
                s += " ";
            } // empty dungeon
            if(myDungeon[theRow][i].getMyEastRoom() == null){
                s += "*";
            } else{
                s += "|";
            }
        }
        s += "\n";
        return s;
    }

    public String getMidString(final int theRow){
        String s = "*";
        for (int i = 0; i < myDungeonRows; i++) {
            s += myDungeon[theRow][i].getMyStringToken();
            if(myDungeon[theRow][i].getMyEastRoom() == null){
                s += "*";
            } else{
                s += "|";
            }
        }
        s += "\n";
        return s;
    }

    public Room[][] getMyDungeon() {
        return myDungeon;
    }

    public Room getMyRoom() {
        return myDungeon[myHeroRow][myHeroCol];
    }
}