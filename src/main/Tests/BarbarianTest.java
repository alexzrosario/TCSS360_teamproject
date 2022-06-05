package main.Tests;
import main.DungeonCharacter.Barbarian;
import main.DungeonCharacter.Monster;
import main.DungeonCharacter.MonsterFactory;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class BarbarianTest {
    private Barbarian testHero = new Barbarian("testHero");
    private Monster testMonster = new MonsterFactory().createMonster("ogre");

    @RepeatedTest(20)
    void testAttackValue() {
        testHero.setMyHitChance(1.0);
        assertTrue(40 <= testHero.attackValue(testMonster) && 65 >= testHero.attackValue(testMonster));
    }

    @Test
    void testUpdateHealth() {
        testHero.setMyBlockChance(0.0);
        testHero.updateHealth(25);
        assertEquals(100, testHero.getMyHitPoints());
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
        testHero.updateHealth(125);
        assertFalse(testHero.getMyAlive());
    }

    @Test
    void testSpecialSkill() {
        testMonster.setMyHealChance(0.0);
        testHero.setMyAttackSpeed(2);
        testHero.specialSkill(testMonster);
        assertTrue(120 <= testMonster.getMyHitPoints() && 140>= testMonster.getMyHitPoints());
    }

    @Test
    void testRecklessTakeExtraDamage() {
        testHero.setReckless();
        testHero.updateHealth(10);
        assertEquals(112, testHero.getMyHitPoints());
    }

    @Test
    void testRecklessBlockChance() {
        testHero.specialSkill(testMonster);
        assertEquals(0.0, testHero.getMyBlockChance());
    }
}
