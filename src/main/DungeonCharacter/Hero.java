package main.DungeonCharacter;

/**
 * The type Hero.
 */
public abstract class Hero extends DungeonCharacter {
    private double myBlockChance;
    private String myImage;
    private final String mySkillName;
    private int myHealingPotions = 0;
    private int myVisionPotions = 0;
    private int myPillars = 0;

    /**
     * Instantiates a new Hero.
     *
     * @param theName        the name
     * @param theHitPoints   the hit points
     * @param theMinDam      the min dam
     * @param theMaxDam      the max dam
     * @param theAttackSpeed the attack speed
     * @param theHitChance   the hit chance
     * @param theBlockChance the block chance
     * @param skillName      the skill name
     * @param theImage       the image
     */
    public Hero(final String theName, final int theHitPoints, final int theMinDam, final int theMaxDam,
                final int theAttackSpeed, final double theHitChance, final double theBlockChance, String skillName, String theImage) {

        super(theName, theHitPoints, theMinDam, theMaxDam, theAttackSpeed, theHitChance);
        this.myBlockChance = theBlockChance;
        this.mySkillName = skillName;
        this.myImage = theImage;
    }

    /**
     * Updates the Health of the Hero
     *
     * @param theDamage
     */
    @Override
    public void updateHealth(final int theDamage) {
        if (Math.random() > myBlockChance) {
            this.setMyHitPoints(this.getMyHitPoints() - theDamage);
            if(this.getMyHitPoints() <= 0){
                setMyAlive();
            }
        }
        else System.out.println(getMyName() + " blocked the attack");
    }

    /**
     * Battle menu.
     *
     * @param theMonster the monster
     * @param theChoice  the choice
     */
    public void battleMenu(final DungeonCharacter theMonster, final int theChoice) {
        switch(theChoice) {
            case 1 ->
                    basicAttack(theMonster);
            case 2 ->
                    specialSkill(theMonster);
            default -> {
                if(theChoice != 3) {
                    System.out.println("Invalid Choice");
                }
            }
        }
    }

    /**
     * Special skill.
     *
     * @param theTarget the target
     */
    abstract void specialSkill(final DungeonCharacter theTarget);

    /**
     * Gets my block chance.
     *
     * @return the block chance
     */
    public double getMyBlockChance() {
        return myBlockChance;
    }

    /**
     * Sets my block chance.
     *
     * @param theBlockChance the block chance
     */
    public void setMyBlockChance(final double theBlockChance) {
        this.myBlockChance = theBlockChance;
    }

    /**
     * Gets my image.
     *
     * @return the image
     */
    public String getMyImage() {
        return myImage;
    }

    /**
     * Sets my image.
     *
     * @param theImage the image
     */
    public void setMyImage(final String theImage) {
        this.myImage = theImage;
    }

    /**
     * Gets my healing potions.
     *
     * @return the healing potions
     */
    public int getMyHealingPotions() {
        return myHealingPotions;
    }

    /**
     * Sets my healing potions.
     *
     * @param theHealingPotions the healing potions
     */
    public void setMyHealingPotions(final int theHealingPotions) {
        this.myHealingPotions = theHealingPotions;
    }

    /**
     * Gets my vision potions.
     *
     * @return the vision potions
     */
    public int getMyVisionPotions() {
        return myVisionPotions;
    }

    /**
     * Sets my vision potions.
     *
     * @param theVisionPotions the vision potions
     */
    public void setMyVisionPotions(final int theVisionPotions) {
        this.myVisionPotions = theVisionPotions;
    }

    /**
     * Gets my pillars.
     *
     * @return the my pillars
     */
    public int getMyPillars() {
        return myPillars;
    }

    /**
     * Sets my pillars.
     *
     * @param thePillars the pillars
     */
    public void setMyPillars(final int thePillars) {
        this.myPillars = thePillars;
    }

    /**
     * Gets my skill name.
     *
     * @return the skill name
     */
    public String getMySkillName() {
        return mySkillName;
    }

    /**
     * Use skill.
     *
     * @param theMonster the monster
     */
    public void useSkill(final DungeonCharacter theMonster) {
        specialSkill(theMonster);
    }

    /**
     * @return class name
     */
    public abstract String getHeroClass();
}
