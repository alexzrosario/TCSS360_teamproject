package main.DungeonCharacter;

abstract class Hero extends DungeonCharacter {
    private double myBlockChance;

    public Hero(final String theName, final int theHitPoints, final int theMinDam, final int theMaxDam,
                final int theAttackSpeed, final double theHitChance, final double theBlockChance) {

        super(theName, theHitPoints, theMinDam, theMaxDam, theAttackSpeed, theHitChance);
        this.myBlockChance = theBlockChance;
    }

    abstract void specialSkill(final DungeonCharacter theTarget);

    public double getMyBlockChance() {
        return myBlockChance;
    }

    public void setMyBlockChance(double myBlockChance) {
        this.myBlockChance = myBlockChance;
    }
}
