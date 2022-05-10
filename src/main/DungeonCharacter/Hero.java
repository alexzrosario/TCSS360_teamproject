package main.DungeonCharacter;

public abstract class Hero extends DungeonCharacter {
    private double myBlockChance;
    private String myImage;
    private int myHealingPotions = 0;
    private int myVisionPotions = 0;
    private int myPillars = 0;

    public Hero(final String theName, final int theHitPoints, final int theMinDam, final int theMaxDam,
                final int theAttackSpeed, final double theHitChance, final double theBlockChance, String theImage) {

        super(theName, theHitPoints, theMinDam, theMaxDam, theAttackSpeed, theHitChance);
        this.myBlockChance = theBlockChance;
        this.myImage = theImage;
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
}
