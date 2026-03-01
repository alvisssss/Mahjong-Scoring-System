package persistence;

import java.io.IOException;
import org.json.JSONObject;

import model.AccountList;

// Represents a reader that reads accounts from JSON data stored in file
public class JsonAccountsReader { // Structure based on JsonSerializationDemo

    // EFFECTS: constructs reader to read from source file
    public JsonAccountsReader(String source) {
        // stub
    }

    // EFFECTS: reads accountlist from file and returns it;
    // throws IOException if an error occurs reading data from file
    public AccountList read() throws IOException {
        return new AccountList(); // stub
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        return ""; // stub
    }

    // EFFECTS: parses list of accounts from JSON object and returns it
    private AccountList parseAccountList(JSONObject jsonObject) {
        return new AccountList(); // stub
    }

    // MODIFIES: accountlist
    // EFFECTS: parses an account from JSON object and adds it to accountlist
    private void addAccount(AccountList accountlist, JSONObject jsonObject) {
        // stub
    }
}
