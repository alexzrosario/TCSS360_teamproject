package main.Tests;

import main.DungeonCharacter.Ogre;
import main.DungeonCharacter.Warrior;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarriorTest {
    private Warrior testWarrior = new Warrior("testWarrior");
    private Ogre testMonster = new Ogre();

    @RepeatedTest(20)
    void testAttackValue() {
        assertTrue(35 <= testWarrior.attackValue(testMonster) && 60 >= testWarrior.attackValue(testMonster));
    }

    @Test
    void testUpdateHealth() {
        testWarrior.updateHealth(25);
        assertEquals(100, testWarrior.getMyHitPoints());
    }

    @Test
    void testIsAliveFalse() {
        testWarrior.updateHealth(75);
        assertFalse(testWarrior.getMyAlive());
    }

}