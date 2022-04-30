import java.util.Random;

abstract class Monster extends DungeonCharacter{
    double myHealChance;
    int myMinHeal;
    int myMaxHeal;

    public Monster(String theName, int theHitPoints, int theMinDam, int theMaxDam, int theAttackSpeed, double theHitChance, double theHealChance, int theMinHeal, int theMaxHeal) {
        super(theName, theHitPoints, theMinDam, theMaxDam, theAttackSpeed, theHitChance);
        this.myHealChance = theHealChance;
        this.myMinHeal = theMinHeal;
        this.myMaxHeal = theMaxHeal;
    }

    //abstract int attack();

    void heal() {
        Random r = new Random();
        int healChanceRoll = r.nextInt(100) + 1;
        if (healChanceRoll >= 100*(1-myHealChance)) {
            int healRoll = r.nextInt(myMaxHeal - myMinHeal + 1) + myMinHeal;
            setMyHitPoints(Math.min(healRoll + myHitPoints, MAX_HEALTH));
        }
    }

    public double getMyHealChance() {
        return myHealChance;
    }

    public void setMyHealChance(double myHealChance) {
        this.myHealChance = myHealChance;
    }
}
