package main.DungeonCharacter;

import java.util.Random;

abstract class DungeonCharacter {
    private String myName;
    private int myHitPoints;
    private int myMinDam;
    private int myMaxDam;
    private int myAttackSpeed;
    private double myHitChance;
    private boolean myAlive = true; // alive by default
    private final int MY_MAX_HEALTH; //used for priestess heal

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
    public void basicAttack(final DungeonCharacter theTarget){
        int attacks = 1;
        if(this.getMyAttackSpeed() > theTarget.getMyAttackSpeed()) {
            attacks = this.getMyAttackSpeed() / theTarget.getMyAttackSpeed();
        }
        for (int i = 0; i < attacks; i++) {
            attackValue(theTarget);
        }
    }

    public int attackValue(final DungeonCharacter theTarget) {
        Random r = new Random();
        int damageRoll = 0;
        if (Math.random() <= this.getMyHitChance()) {
            damageRoll = r.nextInt(this.getMyMinDam(), this.getMyMaxDam() + 1);
            damageCheck(damageRoll,theTarget);
        }
        //for testing - correct damage
        System.out.println(damageRoll);
        return damageRoll;
    }

    public void damageCheck(int theDamageRoll, final DungeonCharacter theTarget){
        if (theTarget instanceof Hero) {
            if (Math.random() <= ((Hero) theTarget).getMyBlockChance()) {
                //for testing - block
                System.out.println("Attack blocked by hero.");
            } else {
                theTarget.updateHealth(theDamageRoll);
            }
        }else if(theTarget instanceof Monster) {
            if(Math.random() <= ((Monster) theTarget).getMyHealChance()){
                //for testing - heal
                theTarget.updateHealth(theDamageRoll);
                System.out.println("Monster Healed.");

                if(theTarget.getMyAlive()) { // if monster is alive
                    ((Monster) theTarget).heal();
                }
            }else{
                theTarget.updateHealth(theDamageRoll);
            }
        }
    }

    public void updateHealth(final int theDamage){
       this.setMyHitPoints(this.getMyHitPoints() - theDamage);
       if(this.getMyHitPoints() <= 0){
           setMyAlive();
       }
    }
    public void setMyAlive() {this.myAlive = false;}

    public boolean getMyAlive() {
        return myAlive;
    }

    public String getMyName() {
        return myName;
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
