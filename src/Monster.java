abstract class Monster extends DungeonCharacter{
    double myHealChance;

    public Monster(String theName, int theHitPoints, int theMinDam, int theMaxDam, int theAttackSpeed, double theHitChance, double theHealChance) {
        super(theName, theHitPoints, theMinDam, theMaxDam, theAttackSpeed, theHitChance);
        this.myHealChance = theHealChance;
    }

    abstract void heal();

    public double getMyHealChance() {
        return myHealChance;
    }

    public void setMyHealChance(double myHealChance) {
        this.myHealChance = myHealChance;
    }
}
