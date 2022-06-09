package main.DungeonCharacter;


import java.io.Serializable;
import java.util.Random;

/**
 * The type Dungeon character.
 */
public abstract class DungeonCharacter implements Serializable {
    private String myName;
    private int myHitPoints;
    private int myMinDam;
    private int myMaxDam;
    private int myAttackSpeed;
    private double myHitChance;
    private boolean myAlive = true; // alive by default
    private final int MY_MAX_HEALTH; //used for priestess heal
    private static final long serialVersionUID = 3536060713340084481L;

    /**
     * Instantiates a new Dungeon character.
     *
     * @param theName        the name
     * @param theHitPoints   the hit points
     * @param theMinDam      the min dam
     * @param theMaxDam      the max dam
     * @param theAttackSpeed the attack speed
     * @param theHitChance   the hit chance
     */
    public DungeonCharacter(final String theName, final int theHitPoints, final int theMinDam,
                            final int theMaxDam, final int theAttackSpeed, final double theHitChance) {
        this.myName = theName;
        this.myHitPoints = theHitPoints;
        this.MY_MAX_HEALTH = theHitPoints;
        this.myMinDam = theMinDam;
        this.myMaxDam = theMaxDam;
        this.myAttackSpeed = theAttackSpeed;
        this.myHitChance = theHitChance;
    }

    /**
     * Basic attack int.
     *
     * @param theTarget the target
     * @return the int
     */
    public int basicAttack(final DungeonCharacter theTarget){
        int attacks = 1;
        if(this.getMyAttackSpeed() > theTarget.getMyAttackSpeed()) {
            attacks = this.getMyAttackSpeed() / theTarget.getMyAttackSpeed();
        }
        for (int i = 0; i < attacks; i++) {
            attackValue(theTarget);
        }
        return attacks;
    }

    /**
     * Attack value int.
     *
     * @param theTarget the target
     * @return the int
     */
    public int attackValue(final DungeonCharacter theTarget) {
        Random r = new Random();
        int damageRoll = 0;
        if (Math.random() <= this.getMyHitChance()) {
            damageRoll = r.nextInt(this.getMyMinDam(), this.getMyMaxDam() + 1);
            theTarget.updateHealth(damageRoll);
            System.out.println(myName + " hits for " + damageRoll + " damage");
        }
        else {
            System.out.println(myName + " missed their attack");
        }
        return damageRoll;
    }

    /**
     * Update health.
     *
     * @param theDamage the damage
     */
    public void updateHealth(final int theDamage){
    }

    /**
     * Sets my alive.
     */
    public void setMyAlive() {this.myAlive = false;}

    /**
     * Gets my alive.
     *
     * @return the alive status
     */
    public boolean getMyAlive() {
        return myAlive;
    }

    /**
     * Gets my name.
     *
     * @return the name
     */
    public String getMyName() {
        return this.myName;
    }

    /**
     * Sets my name.
     *
     * @param theName the name
     */
    public void setMyName(final String theName) {
        this.myName = theName;
    }

    /**
     * Gets my hit points.
     *
     * @return the hit points
     */
    public int getMyHitPoints() {
        return myHitPoints;
    }

    /**
     * Sets my hit points.
     *
     * @param theHitPoints the hit points
     */
    public void setMyHitPoints(final int theHitPoints) {
        this.myHitPoints = theHitPoints;
    }

    /**
     * Gets my min dam.
     *
     * @return the my min dam
     */
    public int getMyMinDam() {
        return myMinDam;
    }

    /**
     * Sets my min dam.
     *
     * @param myMinDam the my min dam
     */
    public void setMyMinDam(final int myMinDam) {
        this.myMinDam = myMinDam;
    }

    /**
     * Gets my max dam.
     *
     * @return the max dam
     */
    public int getMyMaxDam() {
        return myMaxDam;
    }

    /**
     * Sets my max dam.
     *
     * @param theMaxDam the max dam
     */
    public void setMyMaxDam(final int theMaxDam) {
        this.myMaxDam = theMaxDam;
    }

    /**
     * Gets my attack speed.
     *
     * @return the attack speed
     */
    public int getMyAttackSpeed() {
        return myAttackSpeed;
    }

    /**
     * Sets my attack speed.
     *
     * @param theAttackSpeed the attack speed
     */
    public void setMyAttackSpeed(final int theAttackSpeed) {
        this.myAttackSpeed = theAttackSpeed;
    }

    /**
     * Gets my hit chance.
     *
     * @return the my hit chance
     */
    public double getMyHitChance() {
        return myHitChance;
    }

    /**
     * Sets my hit chance.
     *
     * @param theHitChance the hit chance
     */
    public void setMyHitChance(final double theHitChance) {
        this.myHitChance = theHitChance;
    }

    /**
     * Gets my max health.
     *
     * @return the max health
     */
    public int getMY_MAX_HEALTH() {
        return MY_MAX_HEALTH;
    }
}
