package main.Tests;

import main.DungeonCharacter.Gremlin;
import main.DungeonCharacter.Warrior;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GremlinTest {
    private Gremlin testMonster = new Gremlin();
    private Warrior testHero = new Warrior("testWarrior");

    @RepeatedTest(20)
    void testAttackValue() {
        testMonster.setMyHitChance(1.0);
        assertTrue(15 <= testMonster.attackValue(testHero) && 30 >= testMonster.attackValue(testHero));
    }

    @Test
    void testUpdateHealth() {
        testMonster.setMyHealChance(0.0);
        testMonster.updateHealth(25);
        assertEquals(45, testMonster.getMyHitPoints());
    }

    @RepeatedTest(20)
    void testSuccessfulHeal() {
        testMonster.setMyHealChance(1.0);
        testMonster.updateHealth(50);
        assertTrue(40 <= testMonster.getMyHitPoints() && 60 >= testMonster.getMyHitPoints());
    }

    @Test
    void testSuccessfulHealMaxHealth() {
        testMonster.setMyHealChance(1.0);
        testMonster.setMyHitPoints(69);
        testMonster.heal();
        assertEquals(70, testMonster.getMyHitPoints());
    }

    @Test
    void testIsAliveTrue() {
        testMonster.setMyHealChance(0.0);
        testMonster.updateHealth(25);
        assertTrue(testMonster.getMyAlive());
    }

    @Test
    void testIsAliveFalse() {
        testMonster.setMyHealChance(0.0);
        testMonster.updateHealth(200);
        assertFalse(testMonster.getMyAlive());
    }

    @Test
    void testIsAliveFalseWithHeal() {
        testMonster.setMyHealChance(1.0);
        testMonster.updateHealth(200);
        assertFalse(testMonster.getMyAlive());
    }

}