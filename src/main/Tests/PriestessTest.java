package main.Tests;

import main.DungeonCharacter.Monster;
import main.DungeonCharacter.MonsterFactory;
import main.DungeonCharacter.Priestess;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriestessTest {
    private Priestess testHero = new Priestess("testPriestess");
    private Monster testMonster = new MonsterFactory().createMonster("ogre");

    @RepeatedTest(20)
    void testAttackValue() {
        testHero.setMyHitChance(1.0);
        assertTrue(25 <= testHero.attackValue(testMonster) && 45 >= testHero.attackValue(testMonster));
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
        testHero.updateHealth(75);
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
        testHero.setMyHitPoints(10);
        testHero.specialSkill(testHero);
        assertTrue(35 <= testHero.getMyHitPoints() && 60 >= testHero.getMyHitPoints());
    }

    @Test
    void testSpecialSkillMaxHealth() {
        testHero.setMyHitPoints(74);
        testHero.specialSkill(testHero);
        assertEquals(75, testHero.getMyHitPoints());
    }
    @Test
    void testGetHeroClass() {
        assertEquals("Priestess",testHero.getHeroClass());
    }
}