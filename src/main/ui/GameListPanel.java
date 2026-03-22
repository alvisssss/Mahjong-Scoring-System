package ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Game;
import model.GameList;
// The Game List Panel
public class GameListPanel extends JPanel {

    public static final String[] columnNames = {"Game", "East", "South", "West", "North"};
    
    private DefaultTableModel model;
    private JTable table;
    private ScoringSystem system;
    private MainFrame frame;
    private JScrollPane scrollPane;
    private JButton returnButton;
    private JButton refreshButton;

    // EFFECTS: creates a panel showing all ongoing games with options
    public GameListPanel(MainFrame frame, ScoringSystem system) {
        this.frame = frame;
        this.system = system;
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

        returnButton = new JButton("return");
        refreshButton = new JButton("refresh");
        add(returnButton);
        add(refreshButton);
        add(scrollPane);
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
    }

}
