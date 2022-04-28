abstract class Hero extends DungeonCharacter{

    public Hero(String theName, int theHitPoints, int theMinDam, int theMaxDam, int theAttackSpeed, double theHitChance, double theBlockChance) {
        super(theName, theHitPoints, theMinDam, theMaxDam, theAttackSpeed, theHitChance, theBlockChance);
    }

    abstract void attack();

    abstract void specialSkill();
}
