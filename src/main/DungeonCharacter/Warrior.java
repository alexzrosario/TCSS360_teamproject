package main.DungeonCharacter;

public class Warrior extends Hero {

    public Warrior(final String theName) {
        super(theName, 125, 35, 60, 4, 0.8, 0.2, "WarriorImage.png");
    }

    @Override
    public void specialSkill(final DungeonCharacter theTarget) {
        // Crushing Blow: does 75 to 175 points of damage but only has a 40% chance of succeeding

        //save current hit chance, min damage, max damage
        double currHitChance = this.getMyHitChance();
        int currMinDam = this.getMyMinDam();
        int currMaxDam = this.getMyMaxDam();

        //update with special skill changes
        this.setMyHitChance(0.4);
        this.setMyMinDam(75);
        this.setMyMaxDam(175);

        //call attack
        basicAttack(theTarget);

        //set values back
        this.setMyHitChance(currHitChance);
        this.setMyMinDam(currMinDam);
        this.setMyMaxDam(currMaxDam);

    }
}
