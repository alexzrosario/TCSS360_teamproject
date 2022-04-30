import java.util.Random;

public class Ogre extends Monster{
    public Ogre(String theName, int theHitPoints, int theMinDam, int theMaxDam, int theAttackSpeed, double theHitChance, double theHealChance) {
        super("Ogre", 200, 30, 60, 2, 0.6, 0.1, 30, 60);
    }

    /*@Override
    int attack() {

    }*/

    @Override
    void heal() {
        Random r = new Random();
        int healChanceRoll = r.nextInt(100) + 1;
        if (healChanceRoll >= 100*(1-myHealChance)) {
            int healRoll = r.nextInt(myMaxHeal - myMinHeal - 1) + myMinHeal;
        }
    }
}
