package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Account;
import model.AccountList;

@ExcludeFromJacocoGeneratedReport
// The player menu panel
public class ViewPlayerPanel extends JPanel {

    protected ScoringSystem system;
    protected MainFrame frame;

    private ImageIcon playerIcon;

    private JButton returnButton;
    private JButton refreshButton;
    private JPanel container;
    private JScrollPane scrollPane;

    // EFFECTS: creates a player panel displaying players in the system and return option
    public ViewPlayerPanel(MainFrame frame, ScoringSystem system) {
        this.system = system;
        this.frame = frame;
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setPreferredSize(new Dimension(600, 600));
        setBackground(Color.BLACK);
        JLabel playerText = new JLabel("Players in the system: ");
        returnButton = new JButton("return");
        refreshButton = new JButton("refresh");
        playerText.setForeground(new Color(255, 255, 255));
        add(returnButton);
        add(refreshButton);
        add(playerText);
        container = new JPanel();
        container.setBackground(Color.BLACK);
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        updateTable();
        scrollPane = new JScrollPane(container);
        scrollPane.setBackground(Color.BLACK);
        add(scrollPane);
        setupButtons();
    }

    // EFFECTS: helper method for setting up buttons
    public void setupButtons() {
        returnButton.addActionListener(e -> {
            frame.switchPanel("Main");
        });
        refreshButton.addActionListener(e -> {
            updateTable();
        });        
    }

    // EFFECTS: updates the player list
    public void updateTable() {
        container.removeAll(); 
        AccountList accounts = system.getAccountListClass();
        for (Account account : accounts.getAccountList()) {
            JPanel row = new JPanel(new BorderLayout());
            row.setMaximumSize(new Dimension(200, 30));
            row.setBackground(Color.BLACK);
            ImageIcon originalIcon = new ImageIcon("data/playerIcon" + (new Random().nextInt(6) + 1) + ".jpg"); 
            Image scaledImage = originalIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            playerIcon = new ImageIcon(scaledImage);
            JLabel iconLabel = new JLabel(playerIcon);
            JLabel playerLabel = new JLabel(account.getPlayerId());
            playerLabel.setForeground(Color.WHITE);
            JLabel scoreLabel = new JLabel(account.getPlayerScore().toString());
            scoreLabel.setForeground(Color.WHITE); 
            row.add(iconLabel, BorderLayout.WEST);
            row.add(playerLabel, BorderLayout.CENTER);
            row.add(scoreLabel, BorderLayout.EAST);
            container.add(row);
        }
        container.revalidate(); 
        container.repaint();
    }
}
