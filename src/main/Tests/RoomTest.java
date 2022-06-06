package main.Tests;

import main.DungeonMain.Room;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {

    @Test
    void testDifficultyModifier() {
        Room testRoom = new Room("NORMAL");
        assertEquals(0.25, testRoom.getMyDifficultyModifier());
    }
}
