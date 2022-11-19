package persistence;

import org.json.JSONObject;
// Citation: JsonSerializationDemo

// Creates interface that needs implementation for one function
public interface Writable {

    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}