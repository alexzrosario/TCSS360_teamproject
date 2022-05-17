package main.Tests;

import main.DungeonCharacter.Ogre;
import main.DungeonCharacter.Priestess;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriestessTest {
    private Priestess testPriestess = new Priestess("testPriestess");
    private Ogre testMonster = new Ogre();

    @RepeatedTest(20)
    void testAttackValue() {
        assertTrue(35 <= testPriestess.attackValue(testMonster) && 60 >= testPriestess.attackValue(testMonster));
    }

    @Test
    void testUpdateHealth() {
        testPriestess.updateHealth(25);
        assertEquals(50, testPriestess.getMyHitPoints());
    }

    @Test
    void testIsAliveFalse() {
        testPriestess.updateHealth(75);
        assertFalse(testPriestess.getMyAlive());
    }
}