package main.DungeonCharacter;

public class Samurai extends Hero implements StateResettable {
    private boolean isDefensive = false;
    private DungeonCharacter myTarget;
    private final double MY_STORED_BLOCK_CHANCE = 0.25;
    public Samurai(final String theName) {
        super(theName, 100, 35, 60, 4, 0.8, 0.25, "Counter Attack","SamuraiImage.png");
    }

    @Override
    void specialSkill(DungeonCharacter theTarget) {
        // Counter Attack: Lose your attack for the round to enter a defensive stance. If the monster
        // hits you, and you block it, deal 80 to 120 damage
        // but if you fail to block, you take extra damage
        isDefensive = true;
        myTarget = theTarget;
        this.setMyBlockChance(0.7);
        System.out.println(getMyName() + " enters a defensive stance");
        this.pause(2000);
    }

    @Override
    public void updateHealth(final int theDamage) {
        if (Math.random() > this.getMyBlockChance()) {
            if (isDefensive) { //If the samurai is hit while defensive
                this.setMyHitPoints(this.getMyHitPoints() - (int) (theDamage * 1.5));
                isDefensive = false;
                this.setMyBlockChance(MY_STORED_BLOCK_CHANCE);
            }
            else { //If the samurai is hit while not defensive
                this.setMyHitPoints(this.getMyHitPoints() - theDamage);
                isDefensive = false;
            }
            if(this.getMyHitPoints() <= 0){
                setMyAlive();
            }
        }
        else {
            if (isDefensive) { //When the samurai blocks while defensive
                System.out.println(getMyName() + " counter attacks");
                int currMinDam = this.getMyMinDam();
                int currMaxDam = this.getMyMaxDam();

                //update with special skill changes
                this.setMyMinDam(90);
                this.setMyMaxDam(120);

                //call attack - attacks only once
                attackValue(myTarget);

                //set values back
                this.setMyBlockChance(MY_STORED_BLOCK_CHANCE);
                this.setMyMinDam(currMinDam);
                this.setMyMaxDam(currMaxDam);
                isDefensive = false;
            }
            else System.out.println(getMyName() + " blocked the attack"); //When the samurai blocks while not defensive
        }
    }

    @Override
    public void resetState() {
        isDefensive = false;
    }
}
