package main.DungeonCharacter;

import java.util.Random;

public class Priestess extends Hero {

    public Priestess(final String theName) {
        super(theName, 75, 25, 45, 5, 0.7, 0.3, "Heal", "PriestessImage.png");
    }


    @Override
    public void specialSkill(final DungeonCharacter theTarget) {
        // Heal: heals for 25 to 50 hit points
        Random theRandom = new Random();
        int heal;
        heal = theRandom.nextInt(25, 50+1);
        theTarget.setMyHitPoints(Math.min((getMyHitPoints() + heal), theTarget.getMY_MAX_HEALTH()));
        System.out.println(getMyName() + " heals for " + heal + " health");
    }

    @Override
    public void useSkill(DungeonCharacter theMonster) {
        specialSkill(this);
    }

}

