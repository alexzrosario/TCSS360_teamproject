package main.DungeonCharacter;

public class Warrior extends Hero {

    public Warrior(String theName, int theHitPoints, int theMinDam, int theMaxDam, int theAttackSpeed, double theHitChance, double theBlockChance) {
        super(theName, 125, 35, 60, 4, 0.8, 0.2);
    }

    @Override
    void attack() {

    }

    @Override
    void specialSkill() {
        // Crushing Blow: does 75 to 175 points of damage but only has a 40% chance of succeeding

    }
}
