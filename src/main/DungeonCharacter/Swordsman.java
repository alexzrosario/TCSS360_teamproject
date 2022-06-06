package main.DungeonCharacter;

import java.util.Random;

/**
 * The type Swordsman.
 */
public class Swordsman extends Hero {
    /**
     * Instantiates a new Swordsman.
     *
     * @param theName the the name
     */
    public Swordsman(final String theName) {
        super(theName, 75, 20, 40, 6, 0.8, 0.4, "Blade Flurry","SwordsmanImage.png");
    }

    @Override
    public void specialSkill(DungeonCharacter theTarget) {
        Random theRand = new Random();
        // Blade Flurry: Triple attack speed for one round, but lose 10-15 health
        // Can only be used if the hero is above 15 health
        if (this.getMyHitPoints() <= 15) {
            System.out.println(getMyName() + " does not have enough health to use Blade Flurry and instead does a regular attack");
            basicAttack(theTarget);
        }
        else {
            System.out.println(getMyName() + " does a flurry attack");
            int currAttackSpeed = this.getMyAttackSpeed();
            double currBlockChance = this.getMyBlockChance();

            //update with special skill changes
            this.setMyAttackSpeed(this.getMyAttackSpeed() * 3);

            //call attack - attacks only once
            basicAttack(theTarget);

            //set values back
            this.setMyAttackSpeed(currAttackSpeed);
            //take damage after the attack
            int exhaustionRoll = theRand.nextInt(10, 15) + 1;
            this.setMyBlockChance(0.0);
            this.updateHealth(exhaustionRoll);
            this.setMyBlockChance(currBlockChance);
            System.out.println(getMyName() + " took " + exhaustionRoll + " damage due to exhaustion");
        }
    }
}
