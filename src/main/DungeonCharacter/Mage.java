package main.DungeonCharacter;

public class Mage extends Hero {
    public Mage(final String theName) {
        super(theName, 60, 45, 75, 5, 0.7, 0.3, "Fireball","MageImage.png");
    }

    @Override
    public void specialSkill(DungeonCharacter theTarget) {
        // Fireball: Has a guaranteed chance to hit, but with the cost of reduced damage
        System.out.println(getMyName() + " shoots out a fireball");
        this.pause(2000);
        double currHitChance = this.getMyHitChance();
        int currMinDam = this.getMyMinDam();
        int currMaxDam = this.getMyMaxDam();

        //update with special skill changes
        this.setMyHitChance(1.0);
        this.setMyMinDam(20);
        this.setMyMaxDam(25);

        //call attack - attacks only once
        basicAttack(theTarget);

        //set values back
        this.setMyHitChance(currHitChance);
        this.setMyMinDam(currMinDam);
        this.setMyMaxDam(currMaxDam);
    }
}
