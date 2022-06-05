package main.DungeonCharacter;

import java.util.Random;

public class Thief extends Hero {

    public Thief(final String theName) {
        super(theName, 75, 20, 40, 6, 0.8, 0.4, "Surprise Attack","ThiefImage.png");
    }

    @Override
    public void specialSkill(final DungeonCharacter theTarget) {
        // Surprise Attack: 40 percent chance it is successful.
        // If it is successful, Thief gets an attack and another turn (extra attack) in the current round.
        // There is a 20 percent chance the Thief is caught in which case no attack at all is rendered.
        // The other 40 percent is just a normal attack.
        Random theRandom = new Random();
        float roll = theRandom.nextFloat(1);
        if (roll <= .4){
            basicAttack(theTarget);
            basicAttack(theTarget);
        }else if(.4 < roll && roll <= .8 ){
            basicAttack(theTarget);
        }
    }
}