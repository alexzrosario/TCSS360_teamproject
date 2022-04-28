import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class DungeonFrame {
    JFrame window;
    Container container;
    JPanel gameTitlePanel;
    JLabel gameTitleLabel;
    Font gameTitleFont;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            public void run() {
                DungeonFrame dungeonFrame = new DungeonFrame();
            }
        });
    }

    public DungeonFrame() {

        try {
            gameTitleFont = Font.createFont(Font.TRUETYPE_FONT, new File("Antique Quest St.ttf")).deriveFont(30f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Antique Quest St.ttf")));
        }
        catch (IOException | FontFormatException e) {
        }

        window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);
        window.setVisible(true);
        container = window.getContentPane();

        gameTitlePanel = new JPanel();
        gameTitlePanel.setBounds(150, 25, 500, 150);
        gameTitlePanel.setBackground(Color.GRAY);
        gameTitleLabel = new JLabel("Dungeon Adventure");
        gameTitleLabel.setForeground(Color.BLACK);
        gameTitleLabel.setFont(gameTitleFont);
        gameTitlePanel.add(gameTitleLabel);

        container.add(gameTitlePanel);
    }
}
