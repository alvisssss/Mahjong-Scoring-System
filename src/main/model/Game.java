package model;

import java.util.ArrayList;
import java.util.List;

// Represents a game of mahjong with players in their corresponding seats.
public class Game {

    // REQUIRES: east, south, west, and north are not duplicates
    // EFFECTS: Creates a game with players in corresponding seats in a list, 
    //          starting in East 1 round, no continuance, score 0 for every player, 
    //          and empty list of imcomplete transactions
    public Game(Account east, Account south, Account west, Account north) {
        // stub
    }

    // REQUIRES: faan > 0 AND winner != loser AND 0 <= winner, loser <= 3
    // MODIFIES: this
    // EFFECTS: records the game as deal in and progress the game
    public void dealIn(int winner, int loser, int faan) {
        // stub
    }

    // REQUIRES: faan > 0 AND 0 <= winner <= 3
    // MODIFIES: this
    // EFFECTS: records the game as winner self draw and progress the game
    public void selfDraw(int winner, int faan) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: records the game as decked out and progress the game
    public void deckOut() {
        // stub
    }

    // REQUIRES: 0 <= control <= 1
    // MODIFIES: this
    // EFFECTS: progress the game by going to the next round or adding continuance
    private void progressGame(int control) {
        // stub
    }

    // REQUIRES: east, south, west, north > 0
    // MODIFIES: this
    // EFFECTS: change the corresponding player's score in the game by the given amount
    public void extraTransaction(int east, int south, int west, int north) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: helper method for processing current incomplete transactions
    private void processIncompleteTransactions() {
        // stub
    }

    public String getEastPlayerId() {
        return ""; //stub
    }

    public String getSouthPlayerId() {
        return ""; //stub
    }

    public String getWestPlayerId() {
        return ""; //stub
    }

    public String getNorthPlayerId() {
        return ""; //stub
    }

    public Integer getEastPlayerScore() {
        return 0; //stub
    }

    public Integer getSouthPlayerScore() {
        return 0; //stub
    }

    public Integer getWestPlayerScore() {
        return 0; //stub
    }

    public Integer getNorthPlayerScore() {
        return 0; //stub
    }

    public String getCurrentRound() {
        return ""; //stub
    }

    public int getContinuance() {
        return 0; //stub
    }

    public List<int[]> getIncompleteTransactions() {
        return new ArrayList<>(); //stub
    }




}
