package main.DungeonCharacter;

import java.util.Random;

/**
 * The type Monster.
 */
public class Monster extends DungeonCharacter {
    private double myHealChance;
    private int myMinHeal;
    private int myMaxHeal;

    /**
     * Instantiates a new Monster.
     *
     * @param theName        the the name
     * @param theHitPoints   the the hit points
     * @param theMinDam      the the min dam
     * @param theMaxDam      the the max dam
     * @param theAttackSpeed the the attack speed
     * @param theHitChance   the the hit chance
     * @param theHealChance  the the heal chance
     * @param theMinHeal     the the min heal
     * @param theMaxHeal     the the max heal
     */
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

    /**
     * Heal.
     */
    public void heal() {
        Random theRandom = new Random();
        int healChanceRoll = theRandom.nextInt(100) + 1;
        if (healChanceRoll > 100 * (1 - myHealChance)) {
            int healRoll = theRandom.nextInt(myMaxHeal - myMinHeal + 1) + myMinHeal;
            setMyHitPoints(Math.min(healRoll + getMyHitPoints(), getMY_MAX_HEALTH()));
            System.out.println(getMyName() + " healed for " + healRoll + " health");
        }
    }

    /**
     * Gets my heal chance.
     *
     * @return the my heal chance
     */
    public double getMyHealChance() {
        return myHealChance;
    }

    /**
     * Gets my min heal.
     *
     * @return the my min heal
     */
    public int getMyMinHeal() { return myMinHeal; }

    /**
     * Gets my max heal.
     *
     * @return the my max heal
     */
    public int getMyMaxHeal() { return myMaxHeal; }

    /**
     * Sets my heal chance.
     *
     * @param myHealChance the my heal chance
     */
    public void setMyHealChance(double myHealChance) {
        this.myHealChance = myHealChance;
    }
}
