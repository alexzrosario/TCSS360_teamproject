package main.DungeonCharacter;

import java.util.Random;

abstract class DungeonCharacter {
    private String myName;
    private int myHitPoints;
    private int myMinDam;
    private int myMaxDam;
    private int myAttackSpeed;
    private double myHitChance;
    private boolean myAlive= true; // alive by default

    public DungeonCharacter(final String theName, final int theHitPoints, final int theMinDam,
                            final int theMaxDam, final int theAttackSpeed, final double theHitChance) {
        this.myName = theName;
        this.myHitPoints = theHitPoints;
        this.myMinDam = theMinDam;
        this.myMaxDam = theMaxDam;
        this.myAttackSpeed = theAttackSpeed;
        this.myHitChance = theHitChance;
    }
    public void basicAttack(final DungeonCharacter theTarget){
        int attacks = 1;
        if(this.getMyAttackSpeed() > theTarget.getMyAttackSpeed()){
            if(this.getMyAttackSpeed() == 2 * theTarget.getMyAttackSpeed()){
                attacks = 2;
            } else if(this.getMyAttackSpeed() == 3 * theTarget.getMyAttackSpeed()){
                attacks = 3;
            }
            for (int i = 0; i < attacks; i++) {
                attackValue(theTarget);
            }
        } else {
            attackValue(theTarget);
        }
    }

    public int attackValue(final DungeonCharacter theTarget) {
        Random r = new Random();
        int damageRoll = 0;
        if(Math.random() <= this.getMyHitChance()){
            damageRoll = r.nextInt(this.getMyMinDam(), this.getMyMaxDam()+1);
        }
        if(theTarget instanceof Hero){
            if(Math.random() <= ((Hero) theTarget).getMyBlockChance()){
                damageRoll = 0;
                //for testing - block
                System.out.println("Attack blocked by hero.");
            }else{
                theTarget.updateHealth(damageRoll);
            }
        }else{
            theTarget.updateHealth(damageRoll);
        }
        //for testing - correct damage
        System.out.println(damageRoll);
        return damageRoll;
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

    public void setMyHitPoints(int myHitPoints) {
        this.myHitPoints = myHitPoints;
    }

    public int getMyMinDam() {
        return myMinDam;
    }

    public void setMyMinDam(int myMinDam) {
        this.myMinDam = myMinDam;
    }

    public int getMyMaxDam() {
        return myMaxDam;
    }

    public void setMyMaxDam(int myMaxDam) {
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

    public void setMyHitChance(int myHitChance) {
        this.myHitChance = myHitChance;
    }
}
