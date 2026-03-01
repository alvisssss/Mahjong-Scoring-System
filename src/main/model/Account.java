package model;

import org.json.JSONObject;

import persistence.Writable;

// Represents an account for the scoring system.
public class Account implements Writable {

    private String playerId;
    private int playerScore;

    // REQUIRES: playerId have non-zero length AND different from existing accounts
    // EFFECTS: Creates a new account with the playerId and a score of 0
    public Account(String playerId) {
        this.playerId = playerId;
        playerScore = 0;
    }

    // MODIFIES: this
    // EFFECTS: change the player's score by the given amount
    public void changePlayerScore(int score) {
        playerScore += score;
    }

    public Integer getPlayerScore() {
        return playerScore;
    }


    public String getPlayerId() {
        return playerId;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("playerId", playerId);
        json.put("score", playerScore);
        return json;
    }

}
