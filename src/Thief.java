import java.util.Random;

public class Thief extends Hero{

    public Thief(String theName, int theHitPoints, int theMinDam, int theMaxDam, int theAttackSpeed, double theHitChance, double theBlockChance) {
        super(theName, 75, 20, 40, 6, 0.8, 0.4);
    }

    /*@Override
    int attack() {

    }*/

    @Override
    void specialSkill() {
        // Surprise Attack: 40 percent chance it is successful.
        // If it is successful, Thief gets an attack and another turn (extra attack) in the current round.
        // There is a 20 percent chance the Thief is caught in which case no attack at all is rendered.
        // The other 40 percent is just a normal attack.

    }
}