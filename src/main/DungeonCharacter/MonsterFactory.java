package main.DungeonCharacter;

public class MonsterFactory {
    public Monster createMonster(String theMonster) {
        if (theMonster.equalsIgnoreCase("ogre")) return new Ogre();
        else if (theMonster.equalsIgnoreCase("gremlin")) return new Gremlin();
        else if (theMonster.equalsIgnoreCase("skeleton")) return new Skeleton();
        else throw new IllegalArgumentException("Illegal Argument; Monster must be an Ogre, Gremlin, or Skeleton");
    }
}
