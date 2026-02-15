package model;

// Represents an account for the scoring system.
public class Account {

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
    void changePlayerScore(int score) {
        playerScore += score;
    }

    public Integer getPlayerScore() {
        return playerScore;
    }


    public String getPlayerId() {
        return playerId;
    }

}
