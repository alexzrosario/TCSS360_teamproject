package main.DungeonCharacter;

import java.util.Random;

public abstract class Hero extends DungeonCharacter {
    private double myBlockChance;
    private String myImage;
    private final String mySkillName;
    private int myHealingPotions = 0;
    private int myVisionPotions = 0;
    private int myPillars = 0;

    public Hero(final String theName, final int theHitPoints, final int theMinDam, final int theMaxDam,
                final int theAttackSpeed, final double theHitChance, final double theBlockChance, String skillName, String theImage) {

        super(theName, theHitPoints, theMinDam, theMaxDam, theAttackSpeed, theHitChance);
        this.myBlockChance = theBlockChance;
        this.mySkillName = skillName;
        this.myImage = theImage;
    }

    @Override
    public void updateHealth(final int theDamage) {
        if (Math.random() > myBlockChance) {
            this.setMyHitPoints(this.getMyHitPoints() - theDamage);
            if(this.getMyHitPoints() <= 0){
                setMyAlive();
            }
        }
        else System.out.println(getMyName() + " blocked the attack");
    }

    abstract void specialSkill(final DungeonCharacter theTarget);

    public double getMyBlockChance() {
        return myBlockChance;
    }

    public void setMyBlockChance(double myBlockChance) {
        this.myBlockChance = myBlockChance;
    }

    public String getMyImage() {
        return myImage;
    }

    public void setMyImage(String myImage) {
        this.myImage = myImage;
    }

    public int getMyHealingPotions() {
        return myHealingPotions;
    }

    public void setMyHealingPotions(int myHealingPotions) {
        this.myHealingPotions = myHealingPotions;
    }

    public int getMyVisionPotions() {
        return myVisionPotions;
    }

    public void setMyVisionPotions(int myVisionPotions) {
        this.myVisionPotions = myVisionPotions;
    }

    public int getMyPillars() {
        return myPillars;
    }

    public void setMyPillars(int myPillars) {
        this.myPillars = myPillars;
    }

    /*public int pitDamage() {
        Random r = new Random();
        int damageRoll = 0;
        damageRoll = r.nextInt(1, 20);
        return damageRoll;
    }*/

    public String getMySkillName() {
        return mySkillName;
    }

    public void battleMenu(DungeonCharacter theMonster, int theChoice) {
        switch(theChoice) {
            case 1 ->
                basicAttack(theMonster);
            case 2 ->
                specialSkill(theMonster);
            default ->
                System.out.println("Invalid Choice");
        }
    }

}
