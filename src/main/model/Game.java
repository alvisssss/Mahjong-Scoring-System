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
    private Map<List<Integer>, Integer> incompleteTransactions; // <List.of(from, to), amount>

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
        incompleteTransactions = new HashMap<>();
    }

    // REQUIRES: faan > 0 AND winner != loser AND 0 <= winner, loser <= 3
    // MODIFIES: this
    // EFFECTS: records the game as deal in and progress the game
    public void dealIn(List<Integer> winners, int loser, List<Integer> faan) {
        processIncompleteTransactions(winners, new ArrayList<Integer>(List.of(loser)));
        for (int winner : winners) {
            if (roundCounter % 4 == winner || roundCounter % 4 == loser) { // dealer involved
                incompleteTransactions.put(List.of(loser, winner), 
                        incompleteTransactions.getOrDefault(List.of(loser, winner), 0) 
                        + faan.get(winners.indexOf(winner)) + BASE + 1 + (2 * continuance));
            } else {
                incompleteTransactions.put(List.of(loser, winner), 
                        incompleteTransactions.getOrDefault(List.of(loser, winner), 0) 
                        + faan.get(winners.indexOf(winner)) + BASE);
            }
        }
        progressGame(!winners.contains(roundCounter % 4));
    }

    // REQUIRES: faan > 0 AND 0 <= winner <= 3
    // MODIFIES: this
    // EFFECTS: records the game as winner self draw and progress the game
    public void selfDraw(int winner, int faan) {
        List<Integer> losers = new ArrayList<>(SEAT_NUMBERS);
        losers.remove(winner);
        processIncompleteTransactions(new ArrayList<>(List.of(winner)), losers);
        for (int loser : losers) {
            if (roundCounter % 4 == winner || roundCounter % 4 == loser) { // dealer involved
                incompleteTransactions.put(List.of(loser, winner), 
                        incompleteTransactions.getOrDefault(List.of(loser, winner), 0) 
                        + faan + BASE + 1 + (2 * continuance));
            } else {
                incompleteTransactions.put(List.of(loser, winner), 
                        incompleteTransactions.getOrDefault(List.of(loser, winner), 0) 
                        + faan + BASE);
            }
        }
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
                for (List<Integer> transactionKey: incompleteTransactions.keySet()) {
                    gamePlayerScores.set(transactionKey.get(0), transactionKey.get(0) 
                            - incompleteTransactions.get(transactionKey));
                    gamePlayerScores.set(transactionKey.get(1), transactionKey.get(1) 
                            + incompleteTransactions.get(transactionKey));
                    incompleteTransactions.remove(transactionKey);
                }
                // game ends
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

    // MODIFIES: this
    // EFFECTS: helper method for processing current incomplete transactions
    private void processIncompleteTransactions(List<Integer> winners, List<Integer> losers) {
        for (List<Integer> transactionKey : incompleteTransactions.keySet()) {
            int lastLoser = transactionKey.get(0);
            int lastWinner = transactionKey.get(1);
            int amount = incompleteTransactions.get(transactionKey);
            if (!winners.contains(lastWinner)) { // Winner is not winner of last round
                if (winners.contains(lastLoser) && losers.contains(lastWinner)) {
                    amount = (amount + 1) / 2;
                }
                gamePlayerScores.set(lastLoser, gamePlayerScores.get(lastLoser) - amount);
                gamePlayerScores.set(lastWinner, gamePlayerScores.get(lastWinner) + amount);                
                incompleteTransactions.remove(transactionKey);
            } else { // Winner is winner of last round
                if (losers.contains(lastLoser)) {
                    amount = (int) (amount * 1.5 + 1);
                    incompleteTransactions.put(transactionKey, amount);
                }
            }
        }
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

    public Map<List<Integer>, Integer> getIncompleteTransactions() {
        return incompleteTransactions;
    }




}
