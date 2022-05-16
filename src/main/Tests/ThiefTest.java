package main.Tests;

import main.DungeonCharacter.Ogre;
import main.DungeonCharacter.Thief;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThiefTest {
    private Thief testThief = new Thief("testThief");
    private Ogre testMonster = new Ogre();

    @RepeatedTest(20)
    void testAttackValue() {
        assertTrue(35 <= testThief.attackValue(testMonster) && 60 >= testThief.attackValue(testMonster));
    }

    @Test
    void testUpdateHealth() {
        testThief.updateHealth(25);
        assertEquals(50, testThief.getMyHitPoints());
    }

    @Test
    void testIsAliveFalse() {
        testThief.updateHealth(125);
        assertFalse(testThief.getMyAlive());
    }

}