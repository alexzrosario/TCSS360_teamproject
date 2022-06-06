package main.DungeonCharacter;


import java.io.Serializable;
import java.util.Random;

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

    public void updateHealth(final int theDamage){
    }
    public void setMyAlive() {this.myAlive = false;}

    public boolean getMyAlive() {
        return myAlive;
    }

    public String getMyName() {
        return this.myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }

    public int getMyHitPoints() {
        return myHitPoints;
    }

    public void setMyHitPoints(final int myHitPoints) {
        this.myHitPoints = myHitPoints;
    }

    public int getMyMinDam() {
        return myMinDam;
    }

    public void setMyMinDam(final int myMinDam) {
        this.myMinDam = myMinDam;
    }

    public int getMyMaxDam() {
        return myMaxDam;
    }

    public void setMyMaxDam(final int myMaxDam) {
        this.myMaxDam = myMaxDam;
    }

    public int getMyAttackSpeed() {
        return myAttackSpeed;
    }

    public void setMyAttackSpeed(int myAttackSpeed) {
        this.myAttackSpeed = myAttackSpeed;
    }

    public double getMyHitChance() {
        return myHitChance;
    }

    public void setMyHitChance(final double myHitChance) {
        this.myHitChance = myHitChance;
    }
    public int getMY_MAX_HEALTH() {
        return MY_MAX_HEALTH;
    }
}
