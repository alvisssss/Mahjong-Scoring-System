package ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

// The player menu panel
public class PlayerPanel extends JPanel {

    private JButton returnButton;

    // EFFECTS: creates a player panel displaying players in the system and return option
    public PlayerPanel(MainFrame frame, ScoringSystem system) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setPreferredSize(new Dimension(600, 600));
        setBackground(Color.BLACK);
        JLabel playerText = new JLabel("Players in the system: ");
        returnButton = new JButton("return");
        playerText.setForeground(new Color(255, 255, 255));
        add(playerText);
        add(returnButton);
        returnButton.addActionListener(e -> {
            frame.switchPanel("Main");
        });
    }
}
