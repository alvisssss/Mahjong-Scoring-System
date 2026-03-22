package ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Account;
import model.AccountList;
// The create player panel
public class CreatePlayerPanel extends ViewPlayerPanel {

    private JTextField input;
    private JButton enterButton;
    private JLabel message;

    // EFFECTS: creates a player panel with the option to create an account
    public CreatePlayerPanel(MainFrame frame, ScoringSystem system) {
        super(frame, system);
        JLabel promptText = new JLabel("Please enter new username:");
        promptText.setForeground(Color.WHITE);
        input = new JTextField(0);
        //JPanel 
        enterButton = new JButton("Enter");
        message = new JLabel();
        message.setForeground(Color.WHITE);
        add(promptText);
        add(input);
        add(enterButton);
        add(message);
        handleButton();
    }

    // EFFECTS: helper method for button action handling
    public void handleButton() {
        AccountList accounts = system.geAccountListClass();
        enterButton.addActionListener(e -> {
            String playerId = input.getText();
            if (accounts.getAccountNames().contains(playerId)) {
                message.setText("Username already taken.");
            } else {
                Account account = new Account(playerId);
                accounts.addAccount(account);
                updateTable();
                message.setText("Account with username " + playerId + " created!");
            }
        });
    }

}
