package main.DungeonGUI;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The type Help window.
 */
public class HelpInfoWindow extends JFrame {
    /**
     * Instantiates a new Help window.
     */
    public HelpInfoWindow() {
        this.setTitle("Help Info");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(600, 600);

        JTextArea helpInfoLabel = buildHelpLabel();
        this.add(helpInfoLabel);
        this.setVisible(true);
    }

    /**
     * Build help info label JTextArea
     *
     * @return the JTextArea with help info
     */
    public JTextArea buildHelpLabel() {
        JTextArea helpInfo = new JTextArea();
        helpInfo.setEditable(false);
        try{
            File myFile = new File("src/help.txt");
            Scanner scan = new Scanner(myFile);
            while(scan.hasNextLine()){
                String str = scan.nextLine();
                helpInfo.append(str);
                helpInfo.append("\n");
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return helpInfo;
    }

}
