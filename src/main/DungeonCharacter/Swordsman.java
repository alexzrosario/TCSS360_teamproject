package main.DungeonCharacter;

import java.util.Random;

public class Swordsman extends Hero {
    public Swordsman(final String theName) {
        super(theName, 75, 20, 40, 6, 0.8, 0.3, "Sword Flurry","SwordsmanImage.png");
    }

    @Override
    void specialSkill(DungeonCharacter theTarget) {
        Random theRand = new Random();
        // Sword Flurry: Triple attack speed for one round, but lose 5-15 health
        System.out.println(getMyName() + " does a flurry attack");
        int currAttackSpeed = this.getMyAttackSpeed();
        double currBlockChance = this.getMyBlockChance();

        //update with special skill changes
        this.setMyAttackSpeed(this.getMyAttackSpeed()*3);

        //call attack - attacks only once
        basicAttack(theTarget);

        //set values back
        this.setMyAttackSpeed(currAttackSpeed);
        //take damage after the attack
        int exhaustionRoll = theRand.nextInt(10,15) + 1;
        this.setMyBlockChance(0.0);
        this.updateHealth(exhaustionRoll);
        this.setMyBlockChance(currBlockChance);
        System.out.println(getMyName() + " took " + exhaustionRoll + " damage due to exhaustion");
    }
}
