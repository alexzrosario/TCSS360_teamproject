package main.DungeonGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DungeonGame {

    Choices handleChoice = new Choices();
    DungeonUI ui = new DungeonUI();
    DungeonUIManager um = new DungeonUIManager(ui);

    public static void main(String[] args) {
        new DungeonGame();
    }

    public DungeonGame() {
        ui.DungeonUI(handleChoice);
    }

    public class Choices implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String choice = event.getActionCommand();

            switch(choice) {
                case "start": um.heroSelectScreen();
            }
        }
    }
}
