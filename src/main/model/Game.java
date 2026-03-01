package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Represents a game of mahjong with players in their corresponding seats.
public class Game {

    private static final String[] ROUNDS = {"E1", "E2", "E3", "E4",
            "S1", "S2", "S3", "S4",
            "W1", "W2", "W3", "W4",
            "N1", "N2", "N3", "N4"};
    private static final List<Integer> SEAT_NUMBERS = List.of(0, 1, 2, 3);
    private static final int BASE = 5;

    private List<Account> gamePlayerList;
    private List<Integer> gamePlayerScores;
    private int roundCounter;
    private int continuance;
    private boolean gameEnd;
    private List<Integer> lastTransaction;
    private int lastWinner;
    private List<Integer> lastLosers;

    // REQUIRES: east, south, west, and north are not duplicates
    // EFFECTS: Creates a game with players in corresponding seats in a list,
    // starting in East 1 round, no continuance, score 0 for every player,
    // and empty list of imcomplete transactions
    public Game(Account east, Account south, Account west, Account north) {
        gamePlayerList = new ArrayList<>();
        gamePlayerScores = new ArrayList<>();
        gamePlayerList.add(east);
        gamePlayerList.add(south);
        gamePlayerList.add(west);
        gamePlayerList.add(north);
        gamePlayerScores.add(0);
        gamePlayerScores.add(0);
        gamePlayerScores.add(0);
        gamePlayerScores.add(0);
        roundCounter = 0;
        continuance = 0;
        gameEnd = false;
        lastTransaction = new ArrayList<>(List.of(0, 0, 0, 0));
        lastWinner = -1;
        lastLosers = new ArrayList<>();
    }

    // REQUIRES: faan > 0 AND winner != loser AND 0 <= winner, loser <= 3
    // MODIFIES: this
    // EFFECTS: records the game as deal in and progress the game
    public void dealIn(int winner, int loser, int faan) {
        processLastTransaction(winner, List.of(loser));
        int score = faan + BASE;
        if (roundCounter % 4 == winner || roundCounter % 4 == loser) { // dealer involved
            lastTransaction.set(loser, lastTransaction.get(loser) - score - 1 - (2 * continuance));
            lastTransaction.set(winner, lastTransaction.get(winner) + score + 1 + (2 * continuance));
        } else {
            lastTransaction.set(loser, lastTransaction.get(loser) - score);
            lastTransaction.set(winner, lastTransaction.get(winner) + score);
        }
        progressGame(roundCounter % 4 != winner);
    }

    // REQUIRES: faan > 0 AND 0 <= winner <= 3
    // MODIFIES: this
    // EFFECTS: records the game as winner self draw and progress the game
    public void selfDraw(int winner, int faan) {
        int score = faan + BASE;
        int extra = 0;
        List<Integer> losers = new ArrayList<>(SEAT_NUMBERS);
        losers.remove(winner);
        processLastTransaction(winner, losers);
        for (int loser : losers) {
            if (roundCounter % 4 == winner || roundCounter % 4 == loser) { // dealer involved
                lastTransaction.set(loser, lastTransaction.get(loser) - score - 1 - (2 * continuance));
                extra++;
            } else {
                lastTransaction.set(loser, lastTransaction.get(loser) - score);
            }
        }
        lastTransaction.set(winner, lastTransaction.get(winner) + 3 * score + extra * (1 + (2 * continuance)));
        progressGame(roundCounter % 4 != winner);
    }

    // MODIFIES: this
    // EFFECTS: records the game as decked out and progress the game
    public void deckOut() {
        progressGame(false);
    }

    // MODIFIES: this
    // EFFECTS: progress the game by going to the next round or adding continuance
    private void progressGame(boolean nextRound) {
        if (nextRound) {
            if (roundCounter == 15) {
                extraTransaction(lastTransaction);
                gameEnd = true; // Game ends
                for (Account account : gamePlayerList) {
                    account.changePlayerScore(gamePlayerScores.get(gamePlayerList.indexOf(account)));
                }
            } else {
                roundCounter++;
                continuance = 0;
            }
        } else {
            continuance++;
        }
    }

    // REQUIRES: east, south, west, north > 0
    // MODIFIES: this
    // EFFECTS: change the corresponding player's score in the game by the given amount
    public void extraTransaction(int east, int south, int west, int north) {
        gamePlayerScores.set(0, getEastPlayerScore() + east);
        gamePlayerScores.set(1, getSouthPlayerScore() + south);
        gamePlayerScores.set(2, getWestPlayerScore() + west);
        gamePlayerScores.set(3, getNorthPlayerScore() + north);
    }

    // REQUIRES: scores size is 4
    // MODIFIES: this
    // EFFECTS: change the corresponding player's score in the game by the given amount
    public void extraTransaction(List<Integer> scores) {
        gamePlayerScores.set(0, getEastPlayerScore() + scores.get(0));
        gamePlayerScores.set(1, getSouthPlayerScore() + scores.get(1));
        gamePlayerScores.set(2, getWestPlayerScore() + scores.get(2));
        gamePlayerScores.set(3, getNorthPlayerScore() + scores.get(3));
    }

    // MODIFIES: this
    // EFFECTS: helper method for processing current incomplete transactions
    public void processLastTransaction(int winner, List<Integer> losers) {
            if (winner != lastWinner) { // Winner is not winner of last round
                if (lastLosers.contains(winner) && losers.contains(lastWinner)) {
                    int returnAmount = (int) Math.floor((Math.abs(lastTransaction.get(winner)) * 0.5));
                    lastTransaction.set(winner, lastTransaction.get(winner) + returnAmount);
                    lastTransaction.set(lastWinner, lastTransaction.get(lastWinner) - returnAmount);
                }
                extraTransaction(lastTransaction);
                lastTransaction.replaceAll(n -> 0);
            } else { // Winner is winner of last round
                for (int loser : losers) {
                    if (lastLosers.contains(loser)) {
                        int extraAmount = (int) Math.ceil((Math.abs(lastTransaction.get(loser)) * 0.5));
                        lastTransaction.set(loser, lastTransaction.get(loser) - extraAmount);
                        lastTransaction.set(winner, lastTransaction.get(winner) + extraAmount);
                    }                    
                }
            }
            lastWinner = winner;
            lastLosers = losers;
    }

    public String getEastPlayerId() {
        return gamePlayerList.get(0).getPlayerId();
    }

    public String getSouthPlayerId() {
        return gamePlayerList.get(1).getPlayerId();
    }

    public String getWestPlayerId() {
        return gamePlayerList.get(2).getPlayerId();
    }

    public String getNorthPlayerId() {
        return gamePlayerList.get(3).getPlayerId();
    }

    public Integer getEastPlayerScore() {
        return gamePlayerScores.get(0);
    }

    public Integer getSouthPlayerScore() {
        return gamePlayerScores.get(1);
    }

    public Integer getWestPlayerScore() {
        return gamePlayerScores.get(2);
    }

    public Integer getNorthPlayerScore() {
        return gamePlayerScores.get(3);
    }

    public String getCurrentRound() {
        return ROUNDS[roundCounter] + "-" + continuance;
    }

    public List<Account> getGamePlayerList() {
        return gamePlayerList;
    }

    public boolean getGameEnd() {
        return gameEnd;
    }

    public List<Integer> getLastTransaction() {
        return lastTransaction;
    }




}
