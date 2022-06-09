package main.Tests;

import main.DungeonCharacter.Monster;
import main.DungeonCharacter.MonsterFactory;
import main.DungeonCharacter.Samurai;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SamuraiTest {
    private Samurai testHero = new Samurai("testHero");
    private Monster testMonster = new MonsterFactory().createMonster("ogre");

    @RepeatedTest(20)
    void testAttackValue() {
        testHero.setMyHitChance(1.0);
        assertTrue(35 <= testHero.attackValue(testMonster) && 60 >= testHero.attackValue(testMonster));
    }

    @Test
    void testUpdateHealth() {
        testHero.setMyBlockChance(0.0);
        testHero.updateHealth(25);
        assertEquals(75, testHero.getMyHitPoints());
    }

    @Test
    void testSuccessfulBlock() {
        int healthCheck = testHero.getMyHitPoints();
        testHero.setMyBlockChance(1.0);
        testHero.updateHealth(125);
        assertEquals(healthCheck, testHero.getMyHitPoints());
    }

    @Test
    void testIsAliveTrue() {
        testHero.setMyBlockChance(0.0);
        testHero.updateHealth(25);
        assertTrue(testHero.getMyAlive());
    }

    @Test
    void testIsAliveFalse() {
        testHero.setMyBlockChance(0.0);
        testHero.updateHealth(100);
        assertFalse(testHero.getMyAlive());
    }

    @Test
    void testSpecialSkill() {
        testHero.setMyHitChance(1.0);
        testHero.setMyBlockChance(1.0);
        testHero.setDefensive();
        testMonster.setMyHealChance(0.0);
        testHero.setMyTarget(testMonster);
        testHero.updateHealth(10);
        assertTrue(80 <= testMonster.getMyHitPoints() && 110 >= testMonster.getMyHitPoints());
    }
    @Test
    void testDefensiveFail() {
        testHero.setMyBlockChance(0.0);
        testHero.setDefensive();
        testHero.setMyTarget(testMonster);
        testHero.updateHealth(10);
        assertEquals(85, testHero.getMyHitPoints());
    }
    @Test
    void testGetHeroClass() {
        assertEquals("Samurai",testHero.getHeroClass());
    }
}
