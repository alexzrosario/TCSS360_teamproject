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
    public void battleMenu(DungeonCharacter theMonster, int theChoice) {
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
     * @param myBlockChance the block chance
     */
    public void setMyBlockChance(double myBlockChance) {
        this.myBlockChance = myBlockChance;
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
     * @param myImage the image
     */
    public void setMyImage(String myImage) {
        this.myImage = myImage;
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
     * @param myHealingPotions the healing potions
     */
    public void setMyHealingPotions(int myHealingPotions) {
        this.myHealingPotions = myHealingPotions;
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
     * @param myVisionPotions the vision potions
     */
    public void setMyVisionPotions(int myVisionPotions) {
        this.myVisionPotions = myVisionPotions;
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
     * @param myPillars the pillars
     */
    public void setMyPillars(int myPillars) {
        this.myPillars = myPillars;
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
    public void useSkill(DungeonCharacter theMonster) {
        specialSkill(theMonster);
    }

    /**
     * @return class name
     */
    public abstract String getHeroClass();
}
