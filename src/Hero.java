import java.awt.image.BufferedImage;

abstract class Hero extends DungeonCharacter{
    double myBlockChance;
    String myImage;

    public Hero(String theName, int theHitPoints, int theMinDam, int theMaxDam, int theAttackSpeed, double theHitChance, double theBlockChance, String theImage) {
        super(theName, theHitPoints, theMinDam, theMaxDam, theAttackSpeed, theHitChance);
        this.myBlockChance = theBlockChance;
        this.myImage = theImage;
    }

    //abstract int attack();

    abstract void specialSkill();

    public double getMyBlockChance() {
        return myBlockChance;
    }

    public void setMyBlockChance(double myBlockChance) {
        this.myBlockChance = myBlockChance;
    }
}
