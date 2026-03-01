package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Account;
import model.AccountList;

// Represents a reader that reads accounts from JSON data stored in file
public class JsonAccountsReader { // Structure based on JsonSerializationDemo

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonAccountsReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads accountlist from file and returns it;
    // throws IOException if an error occurs reading data from file
    public AccountList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAccountList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses list of accounts from JSON object and returns it
    private AccountList parseAccountList(JSONObject jsonObject) {
        AccountList accountList = new AccountList();
        JSONArray jsonArray = jsonObject.getJSONArray("accounts");
        for (Object json : jsonArray) {
            JSONObject nextObject = (JSONObject) json;
            addAccount(accountList, nextObject);
        }
        return accountList;
    }

    // MODIFIES: accountList
    // EFFECTS: parses an account from JSON object and adds it to accountList
    private void addAccount(AccountList accountList, JSONObject jsonObject) {
        String name = jsonObject.getString("playerId");
        int score = jsonObject.getInt("score");
        Account account = new Account(name);
        account.changePlayerScore(score);
        accountList.addAccount(account);
    }
}
