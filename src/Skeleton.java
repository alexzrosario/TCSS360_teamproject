public class Skeleton extends Monster{
    public Skeleton(String theName, int theHitPoints, int theMinDam, int theMaxDam, int theAttackSpeed, double theHitChance, double theHealChance) {
        super("Skeleton", 100, 30, 50, 3, 0.8, 0.3, 30, 50);
    }

    /*@Override
    int attack() {

    }*/

    @Override
    void heal() {

    }
}