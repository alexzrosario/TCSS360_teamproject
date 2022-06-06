package main.DungeonCharacter;

public class Monk extends Hero {
    public Monk(final String theName) {
        super(theName, 75, 20, 40, 7, 0.8, 0.4, "Fine Strike", "MonkImage.png");
    }

    @Override
    public void specialSkill(DungeonCharacter theTarget) {
        // Fine Strike: does 40 to 60 points of damage but only has a 50% chance of succeeding.
        // The target's chance to hit is permanently reduced if hit
        //save current hit chance, min damage, max damage
        System.out.println(getMyName() + " goes for a fine strike");
        double currHitChance = this.getMyHitChance();
        int currMinDam = this.getMyMinDam();
        int currMaxDam = this.getMyMaxDam();

        //update with special skill changes
        this.setMyHitChance(0.5);
        this.setMyMinDam(40);
        this.setMyMaxDam(60);


        //call attack - attacks only once
        int fineStrikeRoll = attackValue(theTarget);
        if (fineStrikeRoll > 0) {
            theTarget.setMyHitChance(Math.max(0.1, (theTarget.getMyHitChance() * 2)/3));
            System.out.println(theTarget.getMyName() + " chance to hit has been reduced");
        }

        //set values back
        this.setMyHitChance(currHitChance);
        this.setMyMinDam(currMinDam);
        this.setMyMaxDam(currMaxDam);
    }

    public void testFineStrike(DungeonCharacter theTarget){
        theTarget.setMyHitChance(Math.max(0.1, (theTarget.getMyHitChance() * 2)/3));
    }
}
