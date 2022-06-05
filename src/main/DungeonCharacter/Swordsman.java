package main.DungeonCharacter;

import java.util.Random;

public class Swordsman extends Hero {
    public Swordsman(final String theName) {
        super(theName, 75, 20, 40, 6, 0.8, 0.4, "Blade Flurry","SwordsmanImage.png");
    }

    @Override
    public void specialSkill(DungeonCharacter theTarget) {
        Random theRand = new Random();
        // Blade Flurry: Triple attack speed for one round, but lose 10-15 health
        // Can only be used if the hero is above 15 health
        if (this.getMyHitPoints() <= 15) {
            basicAttack(theTarget);
        }
        else {
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
        }
    }
}
