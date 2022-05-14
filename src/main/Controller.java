package main;

import main.DungeonCharacter.*;
import main.DungeonMain.*;

public class Controller {
    private Dungeon myDungeon;
    private Room myCurrRoom;
    private int myRow;
    private int myCol;
    private Hero myHero;
    private boolean myGameDone = false;

    public Controller(){}

    public void startGame(final String theName, final int theClass){
        switch (theClass){
            case 1: // warrior
                myHero = new Warrior(theName);
                break;
            case 2: // priestess
                myHero = new Priestess(theName);
                break;
            case 3: // thief
                myHero = new Thief(theName);
                break;
        }

        myDungeon = new Dungeon(5,5);
        System.out.println(myDungeon.toString());
        System.out.println(myHero.getMyName());
        //traveseDungeon();
    }

    public void traveseDungeon(){
        while(!myGameDone){
            myCurrRoom = myDungeon.getMyRoom();
            //send currentRoom to GUI for input
        }
    }

}