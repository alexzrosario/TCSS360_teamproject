package main.DungeonGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DungeonGame {

    ChoiceController handleChoice = new ChoiceController();
    DungeonUI ui = new DungeonUI();
    DungeonUIManager um = new DungeonUIManager(ui);

    public static void main(String[] args) {
        new DungeonGame();
    }

    public DungeonGame() {
        ui.DungeonUI(handleChoice);
    }

    public class ChoiceController implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String choice = event.getActionCommand();
            um.titleScreen();
            switch (choice) {
                case "start" -> um.nameInputScreen();
                case "name" -> um.heroSelectScreen();
                case "hero" -> um.blankScreen();
            }
        }
    }
}