package main.DungeonGUI;

import main.DungeonCharacter.Hero;

import javax.swing.*;
import java.awt.*;

public class HeroInfoWindow extends JFrame {
    Font heroWindowFont = new Font(Font.MONOSPACED, Font.BOLD, 30);

    public HeroInfoWindow(Hero theHero) {
        this.setTitle("Hero Info");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(600, 600);

        JTextArea heroInfoLabel = buildHeroInfoLabel(theHero);
        heroInfoLabel.setFont(heroWindowFont);
        this.add(heroInfoLabel);
        this.setVisible(true);
    }

    public JTextArea buildHeroInfoLabel(Hero theHero) {
        JTextArea heroInfo = new JTextArea();
        heroInfo.setEditable(false);
        heroInfo.setText("Name: " + theHero.getMyName() + "\n" +
                "Class: " + theHero.getClass().getSimpleName() + "\n" +
                "Health: " + theHero.getMyHitPoints() + "\n" +
                "Attack Speed: " + theHero.getMyAttackSpeed() + "\n" +
                "Damage Range: " + theHero.getMyMinDam() +"-"+ theHero.getMyMaxDam() + "\n" +
                "Hit Chance: " + theHero.getMyHitChance() + "\n" +
                "Pillars: " + theHero.getMyPillars());
        return heroInfo;
    }
}
