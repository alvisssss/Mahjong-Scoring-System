package model;

import java.util.ArrayList;
import java.util.List;

// Represents a list of created accounts in the system.
public class AccountList {

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
}
