package persistence;

import model.GameList;

import java.io.*;

// Represents a writer that writes JSON representation of games to file
public class JsonGamesWriter { // Structure based on JsonSerializationDemo

    // EFFECTS: constructs writer to write to destination file
    public JsonGamesWriter(String destination) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of games to file
    public void write(GameList games) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        // stub
    }
}
