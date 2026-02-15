package model;

import java.util.ArrayList;
import java.util.List;

// Represents a list of ongoing games
public class GameList {

    // EFFECTS: creates a list of ongoing games
    public GameList() {

    }
    
    // REQUIRES: game not already in the list
    // MODIFIES: this
    // EFFECTS: adds the given game to the list
    public void addGame(Game game) {

    }

    // REQUIRES: game already in the list
    // MODIFIES: this
    // EFFECTS: removes the given game from the list
    public void removeGame(Game game) {

    }

    public List<Game> getGameList() {
        return new ArrayList<>();
    }

    

}
