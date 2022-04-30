package main.DungeonCharacter;

abstract class Hero extends DungeonCharacter {
    double myBlockChance;

    public Hero(String theName, int theHitPoints, int theMinDam, int theMaxDam, int theAttackSpeed, double theHitChance, double theBlockChance) {
        super(theName, theHitPoints, theMinDam, theMaxDam, theAttackSpeed, theHitChance);
        this.myBlockChance = theBlockChance;
    }

    abstract void attack();

    abstract void specialSkill();

    public double getMyBlockChance() {
        return myBlockChance;
    }

    public void setMyBlockChance(double myBlockChance) {
        this.myBlockChance = myBlockChance;
    }
}
