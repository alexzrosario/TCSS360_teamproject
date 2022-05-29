package main.DungeonCharacter;

public class Occultist extends Hero {
    public Occultist(final String theName) {
        super(theName, 75, 25, 45, 5, 0.7, 0.3,"Life Steal", "OccultistImage.png");
    }

    @Override
    void specialSkill(DungeonCharacter theTarget) {
        // Life-steal: Attack to deal 25-40 damage, and heal health for the amount of damage you dealt
        System.out.println(getMyName() + " tries to steal " + theTarget.getMyName() + "'s health");
        this.pause(2000);
        int currMinDam = this.getMyMinDam();
        int currMaxDam = this.getMyMaxDam();

        //update with special skill changes
        this.setMyMinDam(25);
        this.setMyMaxDam(40);

        //call attack - attacks only once
        int heal = attackValue(theTarget);
        this.setMyHitPoints(Math.min((getMyHitPoints() + heal), this.getMY_MAX_HEALTH()));
        System.out.println(getMyName() + " heals for " + heal + " health");
        System.out.println(getMyHitPoints());

        //set values back
        this.setMyMinDam(currMinDam);
        this.setMyMaxDam(currMaxDam);
    }
}
