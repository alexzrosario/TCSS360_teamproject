package main.DungeonCharacter;

public class Samurai extends Hero {
    private boolean isDefensive = false;
    private DungeonCharacter myTarget;
    public Samurai(final String theName) {
        super(theName, 100, 35, 60, 4, 0.8, 0.2, "Counter Attack","SamuraiImage.png");
    }

    @Override
    void specialSkill(DungeonCharacter theTarget) {
        // Counter Attack: Lose your attack for the round to enter a defensive stance. If the monster
        // hits you, and you block it, deal 80 to 120 damage
        isDefensive = true;
        myTarget = theTarget;
        this.setMyBlockChance(0.5);
        System.out.println(getMyName() + " enters a defensive stance");
    }

    @Override
    public void updateHealth(final int theDamage) {
        if (Math.random() > this.getMyBlockChance()) {
            this.setMyHitPoints(this.getMyHitPoints() - theDamage);
            isDefensive = false;
            if(this.getMyHitPoints() <= 0){
                setMyAlive();
            }
        }
        else {
            if (isDefensive) {
                System.out.println(getMyName() + " counter attacks");
                int currMinDam = this.getMyMinDam();
                int currMaxDam = this.getMyMaxDam();

                //update with special skill changes
                this.setMyMinDam(80);
                this.setMyMaxDam(120);

                //call attack - attacks only once
                attackValue(myTarget);

                //set values back
                this.setMyBlockChance(0.2);
                this.setMyMinDam(currMinDam);
                this.setMyMaxDam(currMaxDam);
                isDefensive = false;
            }
            else System.out.println(getMyName() + " blocked the attack");
        }
    }
}
