package main.Tests;

import main.DungeonCharacter.Monster;
import main.DungeonCharacter.MonsterFactory;
import main.DungeonCharacter.Occultist;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OccultistTest {
    private Occultist testHero = new Occultist("testHero");
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

    @Test // heal greater than 75
    void testSpecialSkill() {
        testHero.setMyHitChance(1.0);
        testHero.setMyBlockChance(0.0);
        testHero.setMyHitPoints(74);
        testMonster.setMyHealChance(0.0);
        testHero.specialSkill(testMonster);
        assertEquals(75, testHero.getMyHitPoints());
        assertTrue(160 <= testMonster.getMyHitPoints() && 175>= testMonster.getMyHitPoints());
    }

    @Test // heal less than 75
    void testSpecialSkillLess() {
        testHero.setMyHitChance(1.0);
        testHero.setMyBlockChance(0.0);
        testHero.setMyHitPoints(10);
        testMonster.setMyHealChance(0.0);
        testHero.specialSkill(testMonster);
        assertTrue(35 <= testHero.getMyHitPoints() && 50>= testHero.getMyHitPoints());
        assertTrue(160 <= testMonster.getMyHitPoints() && 175>= testMonster.getMyHitPoints());
    }
    @Test
    void testGetHeroClass() {
        assertEquals("Occultist",testHero.getHeroClass());
    }
}
