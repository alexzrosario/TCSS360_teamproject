package main.Tests;

import main.DungeonCharacter.Ogre;
import main.DungeonCharacter.Warrior;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OgreTest {
    private Ogre testMonster = new Ogre();
    private Warrior testHero = new Warrior("testWarrior");

    @RepeatedTest(20)
    void testAttackValue() {
        testMonster.setMyHitChance(1.0);
        assertTrue(30 <= testMonster.attackValue(testHero) && 60 >= testMonster.attackValue(testHero));
    }

    @Test
    void testUpdateHealth() {
        testMonster.setMyHealChance(0.0);
        testMonster.updateHealth(25);
        assertEquals(175, testMonster.getMyHitPoints());
    }

    @RepeatedTest(20)
    void testSuccessfulHeal() {
        testMonster.setMyHealChance(1.0);
        testMonster.updateHealth(100);
        assertTrue(130 <= testMonster.getMyHitPoints() && 160 >= testMonster.getMyHitPoints());
    }

    @Test
    void testSuccessfulHealMaxHealth() {
        testMonster.setMyHealChance(1.0);
        testMonster.setMyHitPoints(199);
        testMonster.heal();
        assertEquals(200, testMonster.getMyHitPoints());
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