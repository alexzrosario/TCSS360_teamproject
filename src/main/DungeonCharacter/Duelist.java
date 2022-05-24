package main.DungeonCharacter;

public class Duelist extends Hero {
    public Duelist(final String theName) {
        super(theName, 75, 20, 40, 6, 0.8, 0.4, "Fine Strike","DuelistImage.png");
    }

    @Override
    void specialSkill(DungeonCharacter theTarget) {
        // Fine Strike: does 50 to 100 points of damage but only has a 50% chance of succeeding.
        // The target cannot heal if struck by this attack
        //save current hit chance, min damage, max damage
        System.out.println(getMyName() + " goes for a fine strike");
        double currHitChance = this.getMyHitChance();
        int currMinDam = this.getMyMinDam();
        int currMaxDam = this.getMyMaxDam();
        double currMonsterHealChance = ((Monster) theTarget).getMyHealChance();

        //update with special skill changes
        this.setMyHitChance(0.5);
        this.setMyMinDam(50);
        this.setMyMaxDam(100);
        ((Monster) theTarget).setMyHealChance(0.0);


        //call attack - attacks only once
        attackValue(theTarget);

        //set values back
        this.setMyHitChance(currHitChance);
        this.setMyMinDam(currMinDam);
        this.setMyMaxDam(currMaxDam);
        ((Monster) theTarget).setMyHealChance(currMonsterHealChance);
    }
}
