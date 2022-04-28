abstract class DungeonCharacter {
    String myName;
    int myHitPoints;
    int myMinDam;
    int myMaxDam;
    int myAttackSpeed;
    int myHitChance;

    public DungeonCharacter(String theName, int theHitPoints, int theMinDam, int theMaxDam, int theAttackSpeed, int theHitChance) {
        this.myName = theName;
        this.myHitPoints = theHitPoints;
        this.myMinDam = theMinDam;
        this.myMaxDam = theMaxDam;
        this.myAttackSpeed = theAttackSpeed;
        this.myHitChance = theHitChance;
    }

    abstract void attack();

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

    public int getMyHitChance() {
        return myHitChance;
    }

    public void setMyHitChance(int myHitChance) {
        this.myHitChance = myHitChance;
    }
}
