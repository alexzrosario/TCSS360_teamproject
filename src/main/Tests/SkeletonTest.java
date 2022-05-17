package main.Tests;

import main.DungeonCharacter.Skeleton;
import main.DungeonCharacter.Warrior;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SkeletonTest {
    private Skeleton testMonster = new Skeleton();
    private Warrior testHero = new Warrior("testWarrior");

    @RepeatedTest(20)
    void testAttackValue() {
        testMonster.setMyHitChance(1.0);
        assertTrue(30 <= testMonster.attackValue(testHero) && 50 >= testMonster.attackValue(testHero));
    }

    @Test
    void testUpdateHealth() {
        testMonster.setMyHealChance(0.0);
        testMonster.updateHealth(25);
        assertEquals(75, testMonster.getMyHitPoints());
    }

    @RepeatedTest(20)
    void testSuccessfulHeal() {
        testMonster.setMyHealChance(1.0);
        testMonster.updateHealth(90);
        assertTrue(40 <= testMonster.getMyHitPoints() && 60 >= testMonster.getMyHitPoints());
    }

    @Test
    void testSuccessfulHealMaxHealth() {
        testMonster.setMyHealChance(1.0);
        testMonster.setMyHitPoints(99);
        testMonster.heal();
        assertEquals(100, testMonster.getMyHitPoints());
    }

    @Test
    void testIsAliveTrue() {
        testMonster.setMyHealChance(0.0);
        testMonster.updateHealth(10);
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