package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
// The main frame of the GUI
public class MainFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel cardPanel;

    // Initializes the frame and the main panel
    public MainFrame(ScoringSystem system) {
        setTitle("Mahjong Scoring System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(600, 600));
        
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        GamePanel gamePanel = new GamePanel(this, system);

        cardPanel.add(new MainPanel(this, system), "Main");
        cardPanel.add(new ViewPlayerPanel(this, system), "ViewPlayer");
        cardPanel.add(new CreatePlayerPanel(this, system), "CreatePlayer");
        cardPanel.add(new RemovePlayerPanel(this, system), "RemovePlayer");
        cardPanel.add(gamePanel, "Game");
        cardPanel.add(new ViewGamesPanel(this, system, gamePanel), "ViewGame");
        cardPanel.add(new CreateGamePanel(this, system), "CreateGame");
        


        cardLayout.show(cardPanel, "Main");
        getContentPane().add(cardPanel, BorderLayout.CENTER);
        pack();
        setVisible(true);
        setResizable(false);
    }

    // EFFECTS: switches the panels in the frame
    public void switchPanel(String name) {
        cardLayout.show(cardPanel, name);
    }

}
