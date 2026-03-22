package ui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
// The main menu panel
public class MainPanel extends JPanel {

    private MainFrame frame;
    private ScoringSystem system;

    private JButton viewPlayerButton;
    private JButton createGameButton;
    private JButton createPlayerButton;
    private JButton removePlayerButton;
    private JButton viewGameButton;
    private JButton loadGameButton;
    private JButton exitButton;
    private JLabel message;

    @SuppressWarnings("methodlength")
    // EFFECTS: creates a main menu panel with buttons and welcoming text
    public MainPanel(MainFrame frame, ScoringSystem system) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setPreferredSize(new Dimension(600, 600));
        setBackground(Color.BLACK);
        JLabel welcomeText = new JLabel("Welcome to the Mahjong Scoring System! Select from below:");
        welcomeText.setForeground(new Color(255, 255, 255));
        viewPlayerButton = new JButton("view players in the system");
        createGameButton = new JButton("create a new game");
        createPlayerButton = new JButton("create a new account");
        removePlayerButton = new JButton("remove an existing account");
        viewGameButton = new JButton("view current ongoing games");
        loadGameButton = new JButton("load unfinished games from file");
        exitButton = new JButton("close the application");
        message = new JLabel();
        message.setForeground(Color.WHITE);
        add(welcomeText);
        add(viewPlayerButton);
        add(createGameButton);
        add(createPlayerButton);
        add(removePlayerButton);
        add(viewGameButton);
        add(loadGameButton);
        add(exitButton);
        add(message);
        this.frame = frame;
        this.system = system;
        setupMethod();
    }

    // EFFECTS: helper method for setup
    private void setupMethod() {
        viewPlayerButton.addActionListener(e -> {
            frame.switchPanel("ViewPlayer");
        });
        createPlayerButton.addActionListener(e -> {
            frame.switchPanel("CreatePlayer");
        });
        removePlayerButton.addActionListener(e -> {
            frame.switchPanel("RemovePlayer");
        });
        viewGameButton.addActionListener(e -> {
            frame.switchPanel("ViewGame");
        });
        createGameButton.addActionListener(e -> {
            frame.switchPanel("CreateGame");
        });
        loadGameButton.addActionListener(e -> {
            if (system.loadGame()) {
                message.setText("Loaded games from " + ScoringSystem.JSON_GAME_STORE + "!");
            } else {
                message.setText("File corrupted or missing at " + ScoringSystem.JSON_GAME_STORE);
            }
        });
        exitButtonHandler();
    }

    // EFFECTS: helper method for setting up exit button action
    private void exitButtonHandler() {
        exitButton.addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(frame, "Would you like to save all unfinished game(s)?", 
                    "Save game", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                system.saveGame();
            }
            system.saveAccount();
            frame.dispose();
            System.exit(0);
        });        
    }
}
