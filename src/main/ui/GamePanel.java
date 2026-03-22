package ui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Game;

@ExcludeFromJacocoGeneratedReport
// The game panel
public class GamePanel extends JPanel {

    private JLabel gameState;
    private JLabel eastPlayer;
    private JLabel southPlayer;
    private JLabel westPlayer;
    private JLabel northPlayer;
    private JLabel lastTransactions;

    private JButton dealInButton;
    private JButton selfDrawButton;
    private JButton deckOutButton;
    
    private JTextField winnerInput;
    private JTextField loserInput;
    private JTextField faanInput;

    private JTextField input;
    private JTextField input2;
    private JTextField input3;
    private JTextField input4;
    private JButton extraButton;

    private JButton returnButton;
    private JButton refreshButton;
    private JButton deleteButton;

    private Game game;
    private MainFrame frame;
    private ScoringSystem system;

    @SuppressWarnings("methodlength")
    // EFFECTS: creates a game panel displaying all info about a game with options
    public GamePanel(MainFrame frame, ScoringSystem system) {
        this.system = system;
        this.frame = frame;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(600, 600));
        setBackground(Color.BLACK);
        gameState = new JLabel();
        gameState.setForeground(Color.WHITE);
        eastPlayer = new JLabel();
        eastPlayer.setForeground(Color.WHITE);
        southPlayer = new JLabel();
        southPlayer.setForeground(Color.WHITE);
        westPlayer = new JLabel();
        westPlayer.setForeground(Color.WHITE);
        northPlayer = new JLabel();
        northPlayer.setForeground(Color.WHITE);
        lastTransactions = new JLabel();
        lastTransactions.setForeground(Color.WHITE);

        JPanel buttonContainer = new JPanel();
        buttonContainer.setBackground(Color.BLACK);
        dealInButton = new JButton("Deal In");
        selfDrawButton = new JButton("Self Draw");
        deckOutButton = new JButton("Deck Out");
        buttonContainer.add(dealInButton);
        buttonContainer.add(selfDrawButton);
        buttonContainer.add(deckOutButton);

        winnerInput = new JTextField(10);
        loserInput = new JTextField(10);
        faanInput = new JTextField(10);
        JPanel scoreContainer = new JPanel();
        scoreContainer.add(winnerInput);
        scoreContainer.add(loserInput);
        scoreContainer.add(faanInput);
        scoreContainer.setPreferredSize(new Dimension(600, 100));

        JPanel extraContainer = new JPanel();
        input = new JTextField("0", 10);
        input2 = new JTextField("0", 10);
        input3 = new JTextField("0", 10);
        input4 = new JTextField("0", 10);
        extraContainer.add(input);
        extraContainer.add(input2);
        extraContainer.add(input3);
        extraContainer.add(input4);
        extraContainer.setPreferredSize(new Dimension(600, 100));
        extraButton = new JButton("Enter");

        returnButton = new JButton("return");
        refreshButton = new JButton("refresh");
        deleteButton = new JButton("delete");
        JLabel extraTransaction = new JLabel("Extra transactions: East | South | West | North");
        extraTransaction.setForeground(Color.WHITE);
        JLabel inputText = new JLabel("Winner pos. | Loser pos. | Winning hand size (faan) [East = 0, North = 3]");
        inputText.setForeground(Color.WHITE);
        add(returnButton);
        add(refreshButton);
        add(deleteButton);
        add(gameState);
        add(eastPlayer);
        add(southPlayer);
        add(westPlayer);
        add(northPlayer);
        add(lastTransactions);
        add(buttonContainer);
        add(inputText);
        add(scoreContainer);
        add(extraTransaction);
        add(extraContainer);
        add(extraButton);
        
        handleTopButtonActions();
        handleGameButtonActions();
    }

    // EFFECTS: helper method for setting up game button actions
    public void handleGameButtonActions() {
        deckOutButton.addActionListener(e -> {
            game.deckOut();
            updateGame(game);
        });
        dealInButton.addActionListener(e -> {
            int winner = Integer.parseInt(winnerInput.getText());
            int loser = Integer.parseInt(loserInput.getText());
            int faan = Integer.parseInt(faanInput.getText());
            game.dealIn(winner, loser, faan);
            updateGame(game);
        });
        selfDrawButton.addActionListener(e -> {
            int winner = Integer.parseInt(winnerInput.getText());
            int faan = Integer.parseInt(faanInput.getText());
            game.selfDraw(winner, faan);
            updateGame(game);
        });
        extraButton.addActionListener(e -> {
            game.extraTransaction(Integer.parseInt(input.getText()), Integer.parseInt(input2.getText()), 
                    Integer.parseInt(input3.getText()), Integer.parseInt(input4.getText()));
            updateGame(game);
        });

    }

    // EFFECTS: helper method for setting up general button actions
    public void updateGame(Game game) {
        this.game = game;
        gameState.setText("Game state: " + game.getCurrentRound());
        eastPlayer.setText("East player: " + game.getEastPlayerId() + " | Score: " + game.getEastPlayerScore());
        southPlayer.setText("South player: " + game.getSouthPlayerId() + " | Score: " + game.getSouthPlayerScore());
        westPlayer.setText("West player: " + game.getWestPlayerId() + " | Score: " + game.getWestPlayerScore());
        northPlayer.setText("North player: " + game.getNorthPlayerId() + " | Score: " + game.getNorthPlayerScore());
        lastTransactions.setText("Last transactions: " + game.getLastTransaction().toString());
        if (game.getGameEnd()) {
            system.getGameListClass().removeGame(this.game);
            frame.switchPanel("ViewGame");
        }
    }

    public void handleTopButtonActions() {
        returnButton.addActionListener(e -> {
            frame.switchPanel("ViewGame");
        });
        refreshButton.addActionListener(e -> {
            updateGame(game);
        });
        deleteButton.addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this game?", 
                    "Delete game", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                system.getGameListClass().removeGame(this.game);
                frame.switchPanel("ViewGame");
            }
        });
    }

}
