package main.DungeonGUI;

public class DungeonUIManager {

    DungeonUI ui;

    public DungeonUIManager(DungeonUI userInterface) {
        ui = userInterface;
    }

    public void titleScreen() {
        ui.gameTitlePanel.setVisible(true);
        ui.gameStartPanel.setVisible(true);

        ui.heroSelectPanel.setVisible(false);
    }

    public void heroSelectScreen() {
        ui.heroSelectPanel.setVisible(true);

        ui.gameTitlePanel.setVisible(false);
        ui.gameStartPanel.setVisible(false);    }

}
