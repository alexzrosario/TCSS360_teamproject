import java.sql.SQLOutput;
import java.util.Random;

public class Room {
    Room myNorthRoom;
    Room myEastRoom;
    Room mySouthRoom;
    Room myWestRoom;
    boolean hasMultipleItems = false;
    boolean hasPit = false;
    boolean hasVisionPotion = false;
    boolean hasHealPotion = false;
    boolean hasAbstractionPillar = false;
    boolean hasEncapsulationPillar = false;
    boolean hasInheritancePillar = false;
    boolean hasPolymorphismPillar = false;
    boolean isEntrance = false;
    boolean isExit = false;

    public Room() {
        Random r = new Random();
        int items = 0;
        int itemRoll = r.nextInt(10) + 1; // Determining if the room will contain a pit
        if (itemRoll == 1) {
            hasPit = true;
            items++;
        }
        itemRoll = r.nextInt(10) + 1; // Determining if the room will contain a vision potion
        if (itemRoll == 1) {
            hasVisionPotion = true;
            items++;
        }
        itemRoll = r.nextInt(10) + 1; // Determining if the room will contain a healing potion
        if (itemRoll == 1) {
            hasHealPotion = true;
            items++;
        }
        if (items > 1) hasMultipleItems = true;
    }

    public String toString() {
        String centerValue = " ";
        String roomString = "";
        if (myNorthRoom != null) roomString += "*-*\n";
        else roomString += "***\n";

        if (myWestRoom != null) roomString += "|";
        else roomString += "*";

        // insert block for center value
        roomString += " ";
        //

        if (myEastRoom != null) roomString += "|\n";
        else roomString += "*\n";

        if (mySouthRoom != null) roomString += "*-*";
        else roomString += "***";

        return roomString;
    }
}
