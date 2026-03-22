package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Account;
import model.AccountList;

// The player menu panel
public class ViewPlayerPanel extends JPanel {

    protected ScoringSystem system;

    private JButton returnButton;
    private JButton refreshButton;
    private JPanel container;
    private JScrollPane scrollPane;

    // EFFECTS: creates a player panel displaying players in the system and return option
    public ViewPlayerPanel(MainFrame frame, ScoringSystem system) {
        this.system = system;
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
            row.setMaximumSize(new Dimension(200, 20));
            row.setBackground(Color.BLACK);
            JLabel playerLabel = new JLabel(account.getPlayerId());
            playerLabel.setForeground(Color.WHITE);
            JLabel scoreLabel = new JLabel(account.getPlayerScore().toString());
            scoreLabel.setForeground(Color.WHITE); 
            row.add(playerLabel, BorderLayout.WEST);
            row.add(scoreLabel, BorderLayout.EAST);
            container.add(row);
        }
        container.revalidate(); 
        container.repaint();
    }
}
