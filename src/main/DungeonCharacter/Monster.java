package main.DungeonCharacter;

import java.util.Random;

abstract class Monster extends DungeonCharacter {
    double myHealChance;
    int myMinHeal;
    int myMaxHeal;

    public Monster(String theName, int theHitPoints, int theMinDam, int theMaxDam, int theAttackSpeed, double theHitChance, double theHealChance, int theMinHeal, int theMaxHeal) {
        super(theName, theHitPoints, theMinDam, theMaxDam, theAttackSpeed, theHitChance);
        this.myHealChance = theHealChance;
        this.myMinHeal = theMinHeal;
        this.myMaxHeal = theMaxHeal;
    }

    public void heal(){
        Random r = new Random();
        int heal;
        heal = r.nextInt(getMyMinHeal(), getMyMaxHeal()+1);
        this.setMyHitPoints(this.getMyHitPoints() + heal);
    }

    public double getMyHealChance() {
        return myHealChance;
    }

    public int getMyMinHeal() { return myMinHeal; }

    public int getMyMaxHeal() { return myMaxHeal; }

    public void setMyHealChance(double myHealChance) {
        this.myHealChance = myHealChance;
    }
}
