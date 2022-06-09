package main.Tests;

import main.DungeonCharacter.Monster;
import main.DungeonCharacter.MonsterFactory;
import main.DungeonCharacter.Mage;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class MageTest {
    private Mage testHero = new Mage("testHero");
    private Monster testMonster = new MonsterFactory().createMonster("ogre");

    @RepeatedTest(20)
    void testAttackValue() {
        testHero.setMyHitChance(1.0);
        assertTrue(45 <= testHero.attackValue(testMonster) && 75 >= testHero.attackValue(testMonster));
    }

    @Test
    void testUpdateHealth() {
        testHero.setMyBlockChance(0.0);
        testHero.updateHealth(25);
        assertEquals(35, testHero.getMyHitPoints());
    }

    @Test
    void testSuccessfulBlock() {
        int healthCheck = testHero.getMyHitPoints();
        testHero.setMyBlockChance(1.0);
        testHero.updateHealth(60);
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
        testHero.updateHealth(60);
        assertFalse(testHero.getMyAlive());
    }

    @Test
    void testSpecialSkill() {
        testMonster.setMyHealChance(0.0);
        testHero.setMyAttackSpeed(2);
        testHero.specialSkill(testMonster);
        assertTrue(175 <= testMonster.getMyHitPoints() && 180>= testMonster.getMyHitPoints());
    }
    @Test
    void testGetHeroClass() {
        assertEquals("Mage",testHero.getHeroClass());
    }

}
