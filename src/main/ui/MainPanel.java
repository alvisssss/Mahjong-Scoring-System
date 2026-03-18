package ui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
// The main menu panel
public class MainPanel extends JPanel {

    public MainPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setPreferredSize(new Dimension(600, 600));
        setBackground(Color.BLACK);
        JLabel welcomeText = new JLabel("Welcome to the Mahjong Scoring System! Select from below:");
        welcomeText.setForeground(new Color(255, 255, 255));
        JButton button1 = new JButton("view players in the system");
        JButton button2 = new JButton("create a new game");
        JButton button3 = new JButton("create a new account");
        JButton button4 = new JButton("remove an existing account");
        JButton button5 = new JButton("view current ongoing games");
        JButton button6 = new JButton("load unfinished games from file");
        JButton button7 = new JButton("close the application");
        add(welcomeText);
        add(button1);
        add(button2);
        add(button3);
        add(button4);
        add(button5);
        add(button6);
        add(button7);
        //button1.addActionListener(this);

        // System.out.println("Welcome to the Mahjong Scoring System! Select from below:");
        // System.out.println("p - view players in the system");
        // System.out.println("c - create a new game");
        // System.out.println("a - create a new account");
        // System.out.println("r - remove an existing account");
        // System.out.println("g - view current ongoing games");
        // System.out.println("l - load unfinished games from file\n");
        // System.out.println("x - close the application\n");
    }




}
