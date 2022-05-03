import java.util.Random;

abstract class DungeonCharacter {
    String myName;
    int myHitPoints;
    int myMinDam;
    int myMaxDam;
    int myAttackSpeed;
    double myHitChance;
    boolean myAlive = true;
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
        if (Math.random() <= this.getMyHitChance()) {
            damageRoll = r.nextInt(this.getMyMinDam(), this.getMyMaxDam() + 1);
        }
        if (theTarget instanceof Hero) {
            if (Math.random() <= ((Hero) theTarget).getMyBlockChance()) {
                damageRoll = 0;
                //for testing - block
                System.out.println("Attack blocked by hero.");
            } else {
                theTarget.updateHealth(damageRoll);
            }
        }else if(theTarget instanceof Monster) {
            if(Math.random() <= ((Monster) theTarget).getMyHealChance()){
                //damageRoll = 0;
                //for testing - heal
                System.out.println("Monster Healed.");
                ((Monster) theTarget).heal();
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
    public void setMyAlive() {
        this.myAlive = false;
    }

    public boolean getMyAlive() {
        return myAlive;
    }

    /*int attack() {
        Random r = new Random();
        int damageRoll = 0;
        for (int i = 0; i < myAttackSpeed; i += 2) {
            int attackRoll = r.nextInt(100) + 1;
            if (attackRoll >= 100*(1-myHitChance)) {
                damageRoll += r.nextInt(myMaxDam - myMinDam + 1) + myMinDam;
            }
        }
        return damageRoll;
    }*/

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

    public int getMAX_HEALTH() {
        return MAX_HEALTH;
    }
}
