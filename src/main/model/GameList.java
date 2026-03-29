package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// Represents a list of ongoing games
public class GameList implements Writable {

    private List<Game> gameList;

    // EFFECTS: creates a list of ongoing games
    public GameList() {
        gameList = new ArrayList<>();
    }
    
    // REQUIRES: game not already in the list
    // MODIFIES: this
    // EFFECTS: adds the given game to the list
    public void addGame(Game game) {
        gameList.add(game);
        EventLog.getInstance().logEvent(new Event("Added game with players: " + game.getEastPlayerId() + ", "
                + game.getSouthPlayerId() + ", "
                + game.getWestPlayerId() + ", and "
                + game.getNorthPlayerId() + " to GameList (Game state: " + game.getCurrentRound() + ")."));
    }

    // REQUIRES: game already in the list
    // MODIFIES: this
    // EFFECTS: removes the given game from the list
    public void removeGame(Game game) {
        gameList.remove(game);
        EventLog.getInstance().logEvent(new Event("Removed game with players: " + game.getEastPlayerId() + ", "
                + game.getSouthPlayerId() + ", "
                + game.getWestPlayerId() + ", and "
                + game.getNorthPlayerId() + " from GameList (Game state: " + game.getCurrentRound() + ")."));
    }

    public List<Game> getGameList() {
        return gameList;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (Game game : gameList) {
            jsonArray.put(game.toJson());
        }
        json.put("games", jsonArray);
        return json;
    }

    

}
