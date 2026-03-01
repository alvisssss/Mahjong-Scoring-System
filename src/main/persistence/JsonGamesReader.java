package persistence;

import java.io.IOException;
import org.json.JSONObject;

import model.GameList;

// Represents a reader the reads games from JSON data stored in file
public class JsonGamesReader { // Structure based on JsonSerializationDemo

    // EFFECTS: constructs reader to read from source file
    public JsonGamesReader(String source) {
        // stub
    }

    // EFFECTS: reads gamelist from file and returns it;
    // throws IOException if an error occurs reading data from file
    public GameList read() throws IOException {
        return new GameList(); // stub
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        return ""; // stub
    }

    // EFFECTS: parses list of games from JSON object and returns it
    private GameList parseGameList(JSONObject jsonObject) {
        return new GameList(); // stub
    }

    // MODIFIES: gameList
    // EFFECTS: parses a game from JSON object and adds it to gameList
    private void addAccount(GameList gameList, JSONObject jsonObject) {
        // stub
    }
}
