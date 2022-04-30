package main.DungeonCharacter;

public class Warrior extends Hero {

    public Warrior(final String theName) {
        super(theName, 125, 35, 60, 4, 0.8, 0.2);
    }

    @Override
    void specialSkill() {
        // Crushing Blow: does 75 to 175 points of damage but only has a 40% chance of succeeding

    }
}
