package main.Tests;

import main.DungeonMain.Dungeon;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class DungeonTest {

    @Test
    void testDifficultyModifier() {
        Dungeon testDungeon = new Dungeon(5,5,"NORMAL");
        assertEquals(5, testDungeon.getMyDungeonCols());
        assertEquals(5, testDungeon.getMyDungeonRows());
        assertEquals("NORMAL", testDungeon.getMyDifficulty());
    }
}
