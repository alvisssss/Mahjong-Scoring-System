package ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Game;
import model.GameList;

@ExcludeFromJacocoGeneratedReport
// The Game List Panel
public class ViewGamesPanel extends JPanel {

    public static final String[] columnNames = {"Game", "East", "South", "West", "North"};
    
    private DefaultTableModel model;
    private JTable table;
    private ScoringSystem system;
    private MainFrame frame;
    private GamePanel gamePanel;
    private JScrollPane scrollPane;
    private JButton returnButton;
    private JButton refreshButton;
    private JButton enterButton;
    private JTextField input;

    @SuppressWarnings("methodlength")
    // EFFECTS: creates a panel showing all ongoing games with options
    public ViewGamesPanel(MainFrame frame, ScoringSystem system, GamePanel gamePanel) {
        this.frame = frame;
        this.system = system;
        this.gamePanel = gamePanel;
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setPreferredSize(new Dimension(600, 600));
        setBackground(Color.BLACK);
        model = new DefaultTableModel(columnNames, 0);
        updateTable();
        table = new JTable(model);
        table.setBackground(Color.BLACK);
        table.setForeground(Color.WHITE);
        table.setEnabled(false);
        scrollPane = new JScrollPane(table);
        scrollPane.setBackground(Color.BLACK);

        input = new JTextField(10);
        JPanel container = new JPanel();
        container.add(input);
        container.setPreferredSize(new Dimension(600, 100));
        returnButton = new JButton("return");
        refreshButton = new JButton("refresh");
        enterButton = new JButton("enter");
        add(returnButton);
        add(refreshButton);
        add(scrollPane);
        JLabel selectText = new JLabel("Select the game number to enter");
        selectText.setForeground(Color.WHITE);
        add(selectText);
        add(container);
        add(enterButton);
        handleButtonAction();
    }

    // EFFECTS: updates the game list
    public void updateTable() {
        model.setRowCount(0);
        int counter = 0;
        GameList games = system.getGameListClass();
        for (Game game : games.getGameList()) {
            Object[] row = {counter, 
                game.getEastPlayerId(),
                game.getSouthPlayerId(),
                game.getWestPlayerId(),
                game.getNorthPlayerId()};
            model.addRow(row);
            counter++;
        }
    }

    // EFFECTS: helper method for handling button actions
    public void handleButtonAction() {
        returnButton.addActionListener(e -> {
            frame.switchPanel("Main");
        });
        refreshButton.addActionListener(e -> {
            updateTable();
        });
        enterButton.addActionListener(e -> {
            Game game = system.getGameListClass().getGameList().get(Integer.parseInt(input.getText()));
            gamePanel.updateGame(game);
            frame.switchPanel("Game");
        });
    }

}
