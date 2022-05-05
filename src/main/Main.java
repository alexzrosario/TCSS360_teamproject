package main;

import main.DungeonCharacter.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Priestess hero = new Priestess("Name");
        Ogre enemy = new Ogre();
        /*
        System.out.println(hero.getMyName());
        System.out.println(hero.getMyHitPoints());
        System.out.println(hero.getMyMinDam());
        System.out.println(hero.getMyMaxDam());
        System.out.println(hero.getMyAttackSpeed());
        System.out.println(hero.getMyHitChance());
        System.out.println(hero.getMyAlive());
        */

     //   System.out.println(enemy.getMyHitPoints());
       // hero.specialSkill(enemy);
        //System.out.println(enemy.getMyHitPoints());

        System.out.println(hero.getMyHitPoints());
        enemy.basicAttack(hero);
        System.out.println(hero.getMyHitPoints());
        hero.specialSkill(hero);
        System.out.println(hero.getMyHitPoints());

        /*
        System.out.println(hero.getMyHitPoints());
        enemy.basicAttack(hero);
        System.out.println(hero.getMyHitPoints());
        */
    }

}
