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

    abstract void heal();

    public double getMyHealChance() {
        return myHealChance;
    }

    public void setMyHealChance(double myHealChance) {
        this.myHealChance = myHealChance;
    }
}
