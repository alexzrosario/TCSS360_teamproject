package main.Tests;

import main.DungeonCharacter.Monster;
import main.DungeonCharacter.MonsterFactory;
import main.DungeonCharacter.Swordsman;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SwordsmanTest {
    private Swordsman testHero = new Swordsman("testHero");
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
        testHero.specialSkill(testMonster);
        assertTrue(60 <= testHero.getMyHitPoints() && 65 >= testHero.getMyHitPoints());
    }
    @Test
    void testGetHeroClass() {
        assertEquals("Swordsman",testHero.getHeroClass());
    }

}
