package main.DungeonGUI;

import main.DungeonCharacter.Hero;

import javax.swing.*;
import java.awt.*;

/**
 * The type Hero info window.
 */
public class HeroInfoWindow extends JFrame {
    /**
     * The Hero window font.
     */
    Font heroWindowFont = new Font(Font.MONOSPACED, Font.BOLD, 30);

    /**
     * Instantiates a new Hero info window.
     *
     * @param theHero the the hero
     */
    public HeroInfoWindow(Hero theHero) {
        this.setTitle("Hero Info");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(600, 600);

        JTextArea heroInfoLabel = buildHeroInfoLabel(theHero);
        heroInfoLabel.setFont(heroWindowFont);
        this.add(heroInfoLabel);

        //puts frame in middle of screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        this.setVisible(true);
    }

    /**
     * Build hero info label j text area.
     *
     * @param theHero the hero
     * @return the j text area
     */
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
