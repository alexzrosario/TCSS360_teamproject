import java.util.Random;

abstract class DungeonCharacter {
    String myName;
    int myHitPoints;
    int myMinDam;
    int myMaxDam;
    int myAttackSpeed;
    double myHitChance;
    final int MAX_HEALTH;

    public DungeonCharacter(String theName, int theHitPoints, int theMinDam, int theMaxDam, int theAttackSpeed, double theHitChance) {
        this.myName = theName;
        this.myHitPoints = theHitPoints;
        this.MAX_HEALTH = theHitPoints;
        this.myMinDam = theMinDam;
        this.myMaxDam = theMaxDam;
        this.myAttackSpeed = theAttackSpeed;
        this.myHitChance = theHitChance;
    }

    int attack() {
        Random r = new Random();
        int damageRoll = 0;
        for (int i = 0; i < myAttackSpeed; i += 2) {
            int attackRoll = r.nextInt(100) + 1;
            if (attackRoll >= 100*(1-myHitChance)) {
                damageRoll += r.nextInt(myMaxDam - myMinDam - 1) + myMinDam;
            }
        }
        return damageRoll;
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
