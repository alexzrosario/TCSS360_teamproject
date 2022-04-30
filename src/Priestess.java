import java.util.Random;

public class Priestess extends Hero{

    public Priestess(String theName, int theHitPoints, int theMinDam, int theMaxDam, int theAttackSpeed, double theHitChance, double theBlockChance) {
        super(theName, 75, 25, 45, 5, 0.7, 0.3);
    }

    /*@Override
    int attack() {

    }*/

    @Override
    void specialSkill() {
        // Heal: heals for 10 to 20 hit points
        Random r = new Random();
        int healRoll = r.nextInt(11) + 10;
        setMyHitPoints(Math.min(healRoll + myHitPoints, MAX_HEALTH));
    }
}

