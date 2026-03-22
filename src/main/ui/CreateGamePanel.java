package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
// The create game panel
public class CreateGamePanel extends ViewPlayerPanel {

    private JTextField input;
    private JTextField input2;
    private JTextField input3;
    private JTextField input4;
    private JButton enterButton;
    private JLabel message;

    // EFFECTS: creates a player panel with the option to create an game
    public CreateGamePanel(MainFrame frame, ScoringSystem system) {
        super(frame, system);
        JLabel promptText = new JLabel("Please enter player usernames (East to North)");
        promptText.setForeground(Color.WHITE);
        input = new JTextField(10);
        input2 = new JTextField(10);
        input3 = new JTextField(10);
        input4 = new JTextField(10);
        JPanel container = new JPanel();
        container.add(input);
        container.add(input2);
        container.add(input3);
        container.add(input4);
        container.setPreferredSize(new Dimension(600, 100));
        enterButton = new JButton("Enter");
        message = new JLabel();
        message.setForeground(Color.WHITE);
        add(promptText);
        add(container);
        add(enterButton);
        add(message);
        handleButton();
    }

    // EFFECTS: helper method for button action handling
    public void handleButton() {
        enterButton.addActionListener(e -> {
            List<String> usernames = new ArrayList<>(List.of(input.getText(), input2.getText(), 
                    input3.getText(), input4.getText()));
            system.createGameHelper(usernames);
            message.setText("Game created.");
        });
    }
}
