package main.DungeonCharacter;

import java.util.Random;

/**
 * The type Priestess.
 */
public class Priestess extends Hero {

    /**
     * Instantiates a new Priestess.
     *
     * @param theName the name
     */
    public Priestess(final String theName) {
        super(theName, 75, 25, 45, 5, 0.7, 0.3, "Heal", "PriestessImage.png");
    }


    /**
     * Special skill for the Priestess
     *
     * @param theTarget the target
     */
    @Override
    public void specialSkill(final DungeonCharacter theTarget) {
        // Heal: heals for 25 to 50 hit points
        Random theRandom = new Random();
        int heal;
        heal = theRandom.nextInt(25, 50+1);
        theTarget.setMyHitPoints(Math.min((getMyHitPoints() + heal), theTarget.getMY_MAX_HEALTH()));
        System.out.println(getMyName() + " heals for " + heal + " health");
    }

    /**
     * Calls Priestess specialSkill()
     *
     * @param theMonster the monster
     */
    @Override
    public void useSkill(final DungeonCharacter theMonster) {
        specialSkill(this);
    }

    /**
     * @return string of the class
     */
    public String getHeroClass() {
        return "Priestess";
    }
}

