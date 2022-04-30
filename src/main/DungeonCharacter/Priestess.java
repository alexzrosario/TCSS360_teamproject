package main.DungeonCharacter;

import java.util.Random;

public class Priestess extends Hero {

    public Priestess(final String theName) {
        super(theName, 75, 25, 45, 5, 0.7, 0.3);
    }


    @Override
    void specialSkill(final DungeonCharacter theTarget) {
        // Heal: heals for 25 to 50 hit points
        Random r = new Random();
        int heal;
        heal = r.nextInt(25, 50+1);
        if((getMyHitPoints() + heal) >= theTarget.getMyMaxHealth()){
            theTarget.setMyHitPoints(theTarget.getMyMaxHealth());
        }else {
            theTarget.setMyHitPoints(getMyHitPoints() + heal);
        }
    }
}

