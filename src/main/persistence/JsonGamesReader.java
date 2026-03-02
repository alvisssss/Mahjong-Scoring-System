package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Account;
import model.AccountList;
import model.Game;
import model.GameList;

// Represents a reader the reads games from JSON data stored in file
public class JsonGamesReader { // Structure based on JsonSerializationDemo

    private String source;
    private AccountList accounts;

    // EFFECTS: constructs reader to read from source file
    public JsonGamesReader(String source, AccountList accounts) {
        this.source = source;
        this.accounts = accounts;
    }

    // EFFECTS: reads gamelist from file and returns it;
    // throws IOException if an error occurs reading data from file
    public GameList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGameList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses list of games from JSON object and returns it
    private GameList parseGameList(JSONObject jsonObject) throws IOException {
        GameList gameList = new GameList();
        JSONArray jsonArray = jsonObject.getJSONArray("games");
        for (Object json : jsonArray) {
            JSONObject nextObject = (JSONObject) json;
            addGame(gameList, nextObject);
        }
        return gameList;
    }

    // MODIFIES: gameList
    // EFFECTS: parses a game from JSON object and adds it to gameList
    private void addGame(GameList gameList, JSONObject jsonObject) throws IOException {
        JSONArray jsonArray1 = jsonObject.getJSONArray("playerIds");
        List<Account> accountsToAdd = new ArrayList<>();
        List<Account> accountList = this.accounts.getAccountList();
        for (Object json : jsonArray1) {
            int index = this.accounts.getAccountNames().indexOf((String) json);
            if (index == -1) {
                throw new IOException("Invalid player ID(s).");
            } else {
                accountsToAdd.add(accountList.get(index));
            }
        }
        Game game = new Game(accountsToAdd.get(0), accountsToAdd.get(1), accountsToAdd.get(2), accountsToAdd.get(3));
        addGameSetup(game, jsonObject);
        gameList.addGame(game);
    }

    private void addGameSetup(Game game, JSONObject jsonObject) {
        List<Integer> scores = new ArrayList<>();
        List<Integer> lastTransaction = new ArrayList<>();
        List<Integer> lastLosers = new ArrayList<>();
        JSONArray jsonArray2 = jsonObject.getJSONArray("playerScores");
        for (Object json : jsonArray2) {
            scores.add((int) json);
        }
        JSONArray jsonArray3 = jsonObject.getJSONArray("lastTransaction");
        for (Object json : jsonArray3) {
            lastTransaction.add((int) json);
        }
        JSONArray jsonArray4 = jsonObject.getJSONArray("lastLosers");
        for (Object json : jsonArray4) {
            lastLosers.add((int) json);
        }
        int round = jsonObject.getInt("roundCounter");
        int continuance = jsonObject.getInt("continuance");
        int lastWinner = jsonObject.getInt("lastWinner");
        game.setup(scores, lastTransaction, round, continuance, lastWinner, lastLosers);
    }
}
