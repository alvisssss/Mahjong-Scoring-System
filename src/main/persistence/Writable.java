package persistence;

import org.json.JSONObject;

public interface Writable { // Structure based on JsonSerializationDemo
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}

