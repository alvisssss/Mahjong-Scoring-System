package ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Account;
import model.AccountList;
// The remove player panel
public class RemovePlayerPanel extends ViewPlayerPanel {

    private JTextField input;
    private JButton removeButton;
    private JLabel message;

    // EFFECTS: creates a player panel with the option to remove an account
    public RemovePlayerPanel(MainFrame frame, ScoringSystem system) {
        super(frame, system);
        JLabel promptText = new JLabel("Please enter username of account to remove:");
        promptText.setForeground(Color.WHITE);
        input = new JTextField(10);
        JPanel container = new JPanel();
        container.add(input);
        container.setPreferredSize(new Dimension(600, 100));
        removeButton = new JButton("Remove");
        message = new JLabel();
        message.setForeground(Color.WHITE);
        add(promptText);
        add(container);
        add(removeButton);
        add(message);
        handleButton();
    }

    // EFFECTS: helper method for button action handling
    public void handleButton() {
        AccountList accounts = system.geAccountListClass();
        removeButton.addActionListener(e -> {
            String playerId = input.getText();
            if (!accounts.getAccountNames().contains(playerId)) {
                message.setText("Invalid username.");
            } else {
                accounts.removeAccount(accounts.getAccountList().get(accounts.getAccountNames().indexOf(playerId)));
                updateTable();
                message.setText("Account with username " + playerId + " removed!");
            }
        });
    }

}
