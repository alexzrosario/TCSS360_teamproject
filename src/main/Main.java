package main;

import main.DungeonMain.Dungeon;
import main.DungeonMain.Room;

public class Main {

    public static void main(String[] args) {
        Dungeon d = new Dungeon(5, 5);
        Room[][] a= d.getMyDungeon();
        System.out.println(d.toString());

        for (int i = 0; i <5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.println(a[i][j].toString());
            }
        }
	    // write your code here
        //Thief hero = new Thief("Name");
        //Skeleton enemy = new Skeleton();
        /*
        System.out.println(hero.getMyName());
        System.out.println(hero.getMyHitPoints());
        System.out.println(hero.getMyMinDam());
        System.out.println(hero.getMyMaxDam());
        System.out.println(hero.getMyAttackSpeed());
        System.out.println(hero.getMyHitChance());
        System.out.println(hero.getMyAlive());
        */

        //System.out.println(enemy.getMyHitPoints());
        //hero.specialSkill(enemy);
        //System.out.println(enemy.getMyHitPoints());
/*
        System.out.println(hero.getMyHitPoints());
        enemy.basicAttack(hero);
        System.out.println(hero.getMyHitPoints());
        hero.specialSkill(hero);
        System.out.println(hero.getMyHitPoints());
*/
        /*
        System.out.println(hero.getMyHitPoints());
        enemy.basicAttack(hero);
        System.out.println(hero.getMyHitPoints());
        */
    }

}
