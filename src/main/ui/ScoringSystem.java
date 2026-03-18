package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Account;
import model.AccountList;
import model.Game;
import model.GameList;
import persistence.JsonAccountsReader;
import persistence.JsonAccountsWriter;
import persistence.JsonGamesReader;
import persistence.JsonGamesWriter;

@ExcludeFromJacocoGeneratedReport
// Mahjong Scoring System application
public class ScoringSystem extends JFrame {

    private static final String JSON_ACCOUNT_STORE = "./data/accounts.json";
    private static final String JSON_GAME_STORE = "./data/games.json";

    private GameList currentGames;
    private AccountList accounts;
    private Scanner input;

    private JsonAccountsReader accountReader;
    private JsonAccountsWriter accountWriter;
    private JsonGamesReader gameReader;
    private JsonGamesWriter gameWriter;

    // EFFECTS: run the application
    public ScoringSystem() {
        super("Mahjong Scoring System");
        currentGames = new GameList();
        accountReader = new JsonAccountsReader(JSON_ACCOUNT_STORE);
        accountWriter = new JsonAccountsWriter(JSON_ACCOUNT_STORE);
        gameWriter = new JsonGamesWriter(JSON_GAME_STORE);
        input = new Scanner(System.in);

        graphicsSetup();

        try {
            accounts = accountReader.read();
            gameReader = new JsonGamesReader(JSON_GAME_STORE, accounts);
            runSystem();
        } catch (IOException e) {
            System.out.println("File corrupted or missing at " + JSON_ACCOUNT_STORE);
        }
    }

    private void graphicsSetup() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(new MainPanel());
        pack();
        setVisible(true);
        setResizable(false);
    }

    // EFFECTS: process user input
    private void runSystem() { // Structure based on TellerApp
        boolean running = true;
        try {
            while (running) {
                displayMainMenu();
                String command = input.next();
                if (command.equals("x")) {
                    accountWriter.open();
                    accountWriter.write(accounts);
                    accountWriter.close();
                    promptSaveGame();
                    running = false;
                } else if (List.of("p", "c", "a", "r", "g", "l").contains(command)) {
                    processMainMenuCommand(command);
                } else {
                    System.out.println("Invalid command.");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File corrupted or missing at " + JSON_ACCOUNT_STORE);
        }
    }

    // EFFECTS: display main menu options to user
    private void displayMainMenu() {
        System.out.println("Welcome to the Mahjong Scoring System! Select from below:");
        System.out.println("p - view players in the system");
        System.out.println("c - create a new game");
        System.out.println("a - create a new account");
        System.out.println("r - remove an existing account");
        System.out.println("g - view current ongoing games");
        System.out.println("l - load unfinished games from file\n");
        System.out.println("x - close the application\n");

    }

    @SuppressWarnings("methodlength")
    // EFFECTS: process command on main menu
    private void processMainMenuCommand(String command) {
        switch (command) {
            case "p":
                displayPlayerMenu();
                break;
            case "c":
                doCreateGame();
                break;
            case "a":
                doCreateAccount();
                break;            
            case "r":
                doRemoveAccount();
                break;
            case "g":
                doViewGames();
                break;
            case "l":
                try {
                    currentGames = gameReader.read();
                    System.out.println("Loaded games from " + JSON_GAME_STORE + "!\n");
                } catch (IOException e) {
                    System.out.println("File corrupted or missing at " + JSON_GAME_STORE);
                }
                break;
        }
    }

    // EFFECTS: display player list to user
    private void displayPlayerMenu() {
        System.out.println("Players in the system:");
        for (Account account : accounts.getAccountList()) {
            System.out.println(account.getPlayerId() + " | Score: " + account.getPlayerScore());
        }
        System.out.println();
    }

    // EFFECTS: creates a new account
    private void doCreateAccount() {
        boolean running = true;
        while (running) {
            System.out.println("Please enter new username: ");
            String username = input.next();
            if (accounts.getAccountNames().contains(username)) {
                System.out.println("Username already taken.");
            } else {
                Account account = new Account(username);
                accounts.addAccount(account);
                System.out.println("Account with username " + username + " created!");
                running = false;
            }
            System.out.println();
        }

    }

    // MODIFIES: this
    // EFFECTS: removes the account with given username.
    private void doRemoveAccount() {
        boolean running = true;
        while (running) {
            if (accounts.getAccountList().isEmpty()) {
                System.out.println("No accounts to remove.\n");
                break;
            }
            displayPlayerMenu();
            System.out.println("Please enter username of account to remove: ");
            String username = input.next();
            if (!accounts.getAccountNames().contains(username)) {
                System.out.println("Invalid username.");
            } else {
                accounts.removeAccount(accounts.getAccountList().get(accounts.getAccountNames().indexOf(username)));
                System.out.println("Account with username " + username + " removed!");
                running = false;
            }
            System.out.println();
        }

    }

    // EFFECTS: creates a new game and adds it to the system.
    private void doCreateGame() {
        while (true) {
            displayPlayerMenu();
            System.out.println("Please enter player usernames from east to north, separated by space: \n");
            String[] usernameArray = input.nextLine().split(" ");
            List<String> usernames = new ArrayList<>(Arrays.asList(usernameArray));
            if (usernames.size() != 4 || new HashSet<>(usernames).size() < usernames.size()) {
                System.out.println("Invalid action.");
            } else {
                boolean invalid = false;
                for (String username : usernames) {
                    if (!accounts.getAccountNames().contains(username)) {
                        System.out.println("Invalid username.");
                        invalid = true;
                        break;
                    }
                }
                if (!invalid) {
                    createGameHelper(usernames);
                    System.out.println("Game created.");
                    break;
                }
            }
        }
    }

    // EFFECTS: helper method for the doCreateGame method
    private void createGameHelper(List<String> usernames) {
        List<Account> accounts = new ArrayList<>();
        for (String username : usernames) {
            Account account = this.accounts.getAccountList().get(this.accounts.getAccountNames().indexOf(username));
            accounts.add(account);
        }
        currentGames.addGame(new Game(accounts.get(0), accounts.get(1), accounts.get(2), accounts.get(3)));
    }

    // EFFECTS: displays the current ongoing games
    private void doViewGames() {
        System.out.println("Current ongoing games:");
        int counter = 0;
        for (Game game : currentGames.getGameList()) {
            System.out.println("Game " + counter + ": " + game.getEastPlayerId() + " " + game.getSouthPlayerId() 
                    + " " + game.getWestPlayerId() + " " + game.getNorthPlayerId());
            counter++;
        }
        while (true) {
            System.out.println("Select the game number to enter (-1 to return to main menu) :");
            try {
                int gameNumber = input.nextInt();
                if (gameNumber == -1) {
                    break;
                } else if (gameNumber >= 0 && gameNumber < counter) {
                    doEditGame(currentGames.getGameList().get(gameNumber));
                    break;
                } 
            } catch (InputMismatchException e) {
                System.out.println("Not a number.");
                input.nextLine();
            }
        }
        System.out.println();
    }

    // EFFECTS: displays game options to user
    private void doEditGame(Game game) {
        while (!game.getGameEnd()) {
            displayGameMenu(game);
            System.out.println("Select from below:");
            System.out.println("d - Deal in");
            System.out.println("s - Self draw");
            System.out.println("o - Deck out");
            System.out.println("e - Extra transactions");
            System.out.println("r - Delete entire game");
            System.out.println("x - return to view games menu");
            String command = input.next();
            if (command.equals("x")) {
                break;
            } else if (command.equals("r")) {
                currentGames.removeGame(game);
                break;
            } else if (List.of("d", "s", "o", "e").contains(command)) {
                processGameMenuCommand(command, game);
            } else {
                System.out.println("Invalid command.");
            }
        }


    }

    // EFFECTS: prompts the user to save all unfinished games
    private void promptSaveGame() {
        System.out.println("Would you like to save all unfinished game(s)? [y/n]\n");
        String response = input.next();
        if (!response.equals("n")) {
            try {
                gameWriter.open();
                gameWriter.write(currentGames);
                gameWriter.close();
                System.out.println("Game(s) saved succesfully at " + JSON_GAME_STORE + "!\n");
            } catch (FileNotFoundException e) {
                System.out.println("Unable to save due to file missing at " + JSON_GAME_STORE + ".");
            }

            
        }
    }

    // EFFECTS: display game information to user
    private void displayGameMenu(Game game) {
        System.out.println("Game state: " + game.getCurrentRound());
        System.out.println("East player: " + game.getEastPlayerId() + " | Score: " + game.getEastPlayerScore());
        System.out.println("South player: " + game.getSouthPlayerId() + " | Score: " + game.getSouthPlayerScore());
        System.out.println("West player: " + game.getWestPlayerId() + " | Score: " + game.getWestPlayerScore());
        System.out.println("North player: " + game.getNorthPlayerId() + " | Score: " + game.getNorthPlayerScore());
        System.out.println("Last transactions: " + game.getLastTransaction().toString());
    }

    // EFFECTS: process command on game menu
    private void processGameMenuCommand(String command, Game game) {
        switch (command) {
            case "d":
                processDealIn(game);
                break;
            case "s":
                processSelfDraw(game);
                break;
            case "o":
                game.deckOut();
                break;            
            case "e":
                processExtraTransaction(game);
                break;
        }
    }

    // EFFECTS: records the game as self draw
    private void processSelfDraw(Game game) {
        while (true) {
            try {
                System.out.println("Please enter winner position [East = 0, North = 3]: ");
                int winner = input.nextInt();        
                System.out.println("Please enter winning hand size (faan): ");
                int faan = input.nextInt();
                game.selfDraw(winner, faan);
                break;
            } catch (InputMismatchException e) {
                System.out.println("Not a number.");
                input.nextLine();
            }            
        }
    }

    // EFFECTS: records the game with extra transactions
    private void processExtraTransaction(Game game) {
        while (true) {
            try {
                System.out.println("Please enter transaction amount of players from east to north, "
                        + "separated by space: ");
                String[] inputLine = input.nextLine().split(" ");
                int[] amountArray = new int[4];
                for (int i = 0; i < 4; i++) {
                    amountArray[i] = Integer.parseInt(inputLine[i]);
                }
                game.extraTransaction(amountArray[0], amountArray[1], amountArray[2], amountArray[3]);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Not a number.");
            }            
        }
    }

    // EFFECTS: records the game as deal in
    private void processDealIn(Game game) {
        System.out.println("Please enter loser position [East = 0, North = 3]: ");
        int loser = input.nextInt();
        System.out.println("Please enter winner position [East = 0, North = 3]: ");
        int winner = input.nextInt();
        System.out.println("Please enter corresponding winning hand size (faan): ");
        int faan = input.nextInt();
        game.dealIn(winner, loser, faan);
    }
}
