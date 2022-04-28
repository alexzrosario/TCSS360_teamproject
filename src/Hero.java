abstract class Hero extends DungeonCharacter{

    public Hero(String theName, int theHitPoints, int theMinDam, int theMaxDam, int theAttackSpeed, int theHitChance) {
        super(theName, theHitPoints, theMinDam, theMaxDam, theAttackSpeed, theHitChance);
    }

    abstract void attack();

    abstract void specialSkll();
}
