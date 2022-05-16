package main.DungeonGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DungeonGame {
    DungeonUI ui = new DungeonUI();
//    ChoiceController handleChoice = new ChoiceController();

    public static void main(String[] args) throws IOException {
        new DungeonGame();
    }

    public DungeonGame() throws IOException {
        ui.DungeonUI();
    }

//    public class ChoiceController implements ActionListener {
//        public void actionPerformed(ActionEvent event) {
//            String choice = event.getActionCommand();
//            um.titleScreen();
//            switch (choice) {
//                case "start" -> um.nameInputScreen();
//                case "name" -> um.heroSelectScreen();
//                case "hero" -> um.dungeonRoomScreen();
//            }
//        }
//    }


}