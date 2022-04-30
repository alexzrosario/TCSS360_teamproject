import java.util.Random;

public class Warrior extends Hero{

    public Warrior(String theName, int theHitPoints, int theMinDam, int theMaxDam, int theAttackSpeed, double theHitChance, double theBlockChance) {
        super(theName, 125, 35, 60, 4, 0.8, 0.2);
    }

    /*@Override
    int attack() {

    }*/

    @Override
    void specialSkill() {
        // Crushing Blow: does 75 to 175 points of damage but only has a 40% chance of succeeding
        Random r = new Random();
        int attackRoll = r.nextInt(100) + 1;
        if (attackRoll >= 100*(0.4)) {
            crushingBlow(r);
        }
    }

    int crushingBlow(Random r) {
        return r.nextInt(101) + 75;
    }
}
