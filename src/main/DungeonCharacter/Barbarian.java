package main.DungeonCharacter;

public class Barbarian extends Hero{
    private boolean isReckless = false;
    public Barbarian(final String theName) {
        super(theName, 125, 40, 65, 4, 0.8, 0.15, "Reckless Attack","BarbarianImage.png");
    }

    @Override
    public void specialSkill(final DungeonCharacter theTarget) {
        // Reckless Attack: Gain an increase to damage, but lose the ability to block and
        // take extra damage until after a round of taking damage
        System.out.println(getMyName() + " recklessly attacks");
        double currHitChance = this.getMyHitChance();
        int currMinDam = this.getMyMinDam();
        int currMaxDam = this.getMyMaxDam();

        //update with special skill changes
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
            if (isReckless) {
                this.setMyHitPoints(this.getMyHitPoints() - (int)(theDamage * 1.5));
                System.out.println(theDamage);
                System.out.println((int)(theDamage * 1.5));
                isReckless = false;
                setMyBlockChance(0.15);
            }
            else this.setMyHitPoints(this.getMyHitPoints() - theDamage);
            if(this.getMyHitPoints() <= 0){
                setMyAlive();
            }
        }
        else {
            System.out.println(getMyName() + " blocked the attack");
            isReckless = false;
        }
    }
}
