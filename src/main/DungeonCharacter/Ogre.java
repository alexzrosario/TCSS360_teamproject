package main.DungeonCharacter;


public class Ogre extends Monster {
    public Ogre(String theName, int theHitPoints, int theMinDam, int theMaxDam, int theAttackSpeed, double theHitChance, double theHealChance) {
        super("main.DungeonCharacter.Ogre", 200, 30, 60, 2, 0.6, 0.1, 30, 60);
    }

    @Override
    void attack() {

    }

    @Override
    void heal() {

    }
}
