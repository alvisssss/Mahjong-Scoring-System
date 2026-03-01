package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// Represents a list of created accounts in the system.
public class AccountList implements Writable{

    private List<Account> accountList;
    
    // EFFECTS: Creates an empty list of accounts
    public AccountList() {
        accountList = new ArrayList<>();
    }

    // REQUIRES: account not already in the list
    // MODIFIES: this
    // EFFECTS: Adds the given account to the list
    public void addAccount(Account account) {
        accountList.add(account);
    }

    // REQUIRES: account already in the list
    // MODIFIES: this
    // EFFECTS: Removes the given account to the list
    public void removeAccount(Account account) {
        accountList.remove(account);
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    // EFFECTS: returns a list of playerIds in the system
    public List<String> getAccountNames() {
        List<String> accountNames = new ArrayList<>();
        for (Account account : accountList) {
            accountNames.add(account.getPlayerId());
        }
        return accountNames;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (Account account : accountList) {
            jsonArray.put(account.toJson());
        }
        json.put("accounts", jsonArray);
        return json;
    }
}
