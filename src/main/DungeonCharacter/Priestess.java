package main.DungeonCharacter;

import java.util.Random;

public class Priestess extends Hero {

    public Priestess(final String theName) {
        super(theName, 75, 25, 45, 5, 0.7, 0.3, "PriestessImage.png");
    }


    @Override
    public void specialSkill(final DungeonCharacter theTarget) {
        // Heal: heals for 25 to 50 hit points
        Random r = new Random();
        int heal;
        heal = r.nextInt(25, 50+1);
        theTarget.setMyHitPoints(Math.min((getMyHitPoints() + heal), theTarget.getMY_MAX_HEALTH()));
    }

    @Override
    public void battleMenu(DungeonCharacter theMonster, int theChoice) {
        switch(theChoice) {
            case 1 ->
                    basicAttack(theMonster);
            case 2 ->
                    specialSkill(this);
            default ->
                    System.out.println("Invalid Choice");
        }
    }
}

