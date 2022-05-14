package main;

import main.DungeonCharacter.*;
import main.DungeonMain.*;

public class Controller {
    private Dungeon myDungeon;
    private Hero myHero;


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
    }
}