package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import persistence.Writable;

// Represents a list of ongoing games
public class GameList implements Writable{

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
    }

    // REQUIRES: game already in the list
    // MODIFIES: this
    // EFFECTS: removes the given game from the list
    public void removeGame(Game game) {
        gameList.remove(game);
    }

    public List<Game> getGameList() {
        return gameList;
    }

    @Override
    public JSONObject toJson() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toJson'");
    }

    

}
