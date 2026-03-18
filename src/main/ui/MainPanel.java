package ui;

import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
// The main menu panel
public class MainPanel extends JPanel {

    private JPanel cardPanel;
    private CardLayout cardLayout;

    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;

    // EFFECTS: creates a main menu panel with buttons and welcoming text
    public MainPanel(JPanel cardPanel, CardLayout cardLayout) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setPreferredSize(new Dimension(600, 600));
        setBackground(Color.BLACK);
        JLabel welcomeText = new JLabel("Welcome to the Mahjong Scoring System! Select from below:");
        welcomeText.setForeground(new Color(255, 255, 255));
        button1 = new JButton("view players in the system");
        button2 = new JButton("create a new game");
        button3 = new JButton("create a new account");
        button4 = new JButton("remove an existing account");
        button5 = new JButton("view current ongoing games");
        button6 = new JButton("load unfinished games from file");
        button7 = new JButton("close the application");
        add(welcomeText);
        add(button1);
        add(button2);
        add(button3);
        add(button4);
        add(button5);
        add(button6);
        add(button7);
        this.cardPanel = cardPanel;
        this.cardLayout = cardLayout;
        setupFunction();
    }

    // EFFECTS: helper method for setup
    private void setupFunction() {
        button1.addActionListener(e -> {
            cardLayout.show(cardPanel, "Player");
        });
        // button2.addActionListener(this);
        // button3.addActionListener(this);
        // button4.addActionListener(this);
        // button5.addActionListener(this);
        // button6.addActionListener(this);
        // button7.addActionListener(this);
    }




}
