package main.Tests;

import main.DungeonCharacter.Monk;
import main.DungeonCharacter.Monster;
import main.DungeonCharacter.MonsterFactory;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MonkTest {
    private Monk testHero = new Monk("testHero");
    private Monster testMonster = new MonsterFactory().createMonster("ogre");

    @RepeatedTest(20)
    void testAttackValue() {
        testHero.setMyHitChance(1.0);
        assertTrue(20 <= testHero.attackValue(testMonster) && 40 >= testHero.attackValue(testMonster));
    }

    @Test
    void testUpdateHealth() {
        testHero.setMyBlockChance(0.0);
        testHero.updateHealth(25);
        assertEquals(50, testHero.getMyHitPoints());
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
        testHero.updateHealth(75);
        assertFalse(testHero.getMyAlive());
    }

    @Test
    void testSpecialSkill() {
        testMonster.setMyHealChance(0.0);
        testHero.setMyAttackSpeed(2);
        testHero.testFineStrike(testMonster);
        assertEquals(Math.round(((0.6 * 2) / 3) * 10) / 10, Math.round(testMonster.getMyHitChance() * 10) / 10);
    }
    @Test
    void testGetHeroClass() {
        assertEquals("Monk",testHero.getHeroClass());
    }
}
