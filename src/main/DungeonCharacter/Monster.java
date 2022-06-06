package main.DungeonCharacter;

import java.util.Random;

public class Monster extends DungeonCharacter {
    private double myHealChance;
    private int myMinHeal;
    private int myMaxHeal;

    public Monster(String theName, int theHitPoints, int theMinDam, int theMaxDam, int theAttackSpeed, double theHitChance, double theHealChance, int theMinHeal, int theMaxHeal) {
        super(theName, theHitPoints, theMinDam, theMaxDam, theAttackSpeed, theHitChance);
        this.myHealChance = theHealChance;
        this.myMinHeal = theMinHeal;
        this.myMaxHeal = theMaxHeal;
    }

    @Override
    public void updateHealth(final int theDamage) {
        this.setMyHitPoints(this.getMyHitPoints() - theDamage);
        if(this.getMyHitPoints() <= 0){
            setMyAlive();
            System.out.println(this.getMyName() + " has died");
        }else{
            heal();
        }
    }

    public void heal() {
        Random theRandom = new Random();
        int healChanceRoll = theRandom.nextInt(100) + 1;
        if (healChanceRoll > 100 * (1 - myHealChance)) {
            int healRoll = theRandom.nextInt(myMaxHeal - myMinHeal + 1) + myMinHeal;
            setMyHitPoints(Math.min(healRoll + getMyHitPoints(), getMY_MAX_HEALTH()));
            System.out.println(getMyName() + " healed for " + healRoll + " health");
        }
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
