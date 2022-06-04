package main.DungeonCharacter;

public class Barbarian extends Hero implements StateResettable {
    private boolean isReckless = false;
    private final double MY_STORED_BLOCK_CHANCE = 0.15;
    public Barbarian(final String theName) {
        super(theName, 125, 40, 65, 4, 0.8, 0.15, "Reckless Attack","BarbarianImage.png");
    }

    @Override
    public void specialSkill(final DungeonCharacter theTarget) {
        // Reckless Attack: Gain an increase to hit chance and damage, but lose the ability to block and
        // take extra damage until after a round of taking damage
        System.out.println(getMyName() + " recklessly attacks");
        double currHitChance = this.getMyHitChance();
        int currMinDam = this.getMyMinDam();
        int currMaxDam = this.getMyMaxDam();

        //update with special skill changes
        this.setMyHitChance(0.9);
        this.setMyMinDam(60);
        this.setMyMaxDam(80);

        //call attack
        basicAttack(theTarget);

        isReckless = true;
        setMyBlockChance(0.0);

        //set values back
        this.setMyHitChance(currHitChance);
        this.setMyMinDam(currMinDam);
        this.setMyMaxDam(currMaxDam);
    }

    @Override
    public void updateHealth(final int theDamage) {
        if (Math.random() > this.getMyBlockChance()) {
            if (isReckless) { //When the barbarian is hit while reckless
                this.setMyHitPoints(this.getMyHitPoints() - (int)(theDamage * 1.5));
                isReckless = false;
                this.setMyBlockChance(MY_STORED_BLOCK_CHANCE);
            }
            else this.setMyHitPoints(this.getMyHitPoints() - theDamage); //When the barbarian is hit while not reckless
            if(this.getMyHitPoints() <= 0){
                setMyAlive();
            }
        }
        else {
            System.out.println(getMyName() + " blocked the attack");
            isReckless = false;
        }
    }

    @Override
    public void resetState() {
        isReckless = false;
    }
}
