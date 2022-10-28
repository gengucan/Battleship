package persistence;


import model.Board;
import model.Ship;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Citation: JsonSerializationDemo

public class JsonRead {
    private String file;

    public JsonRead(String file) {
        this.file = file;
    }

    public Board read() throws IOException {
        String jsonData = readFile(file);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBoard(jsonObject);
    }

    private String readFile(String file) throws IOException {
        StringBuilder builder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(file), StandardCharsets.UTF_8)) {
            stream.forEach(s -> builder.append(s));
        }

        return builder.toString();
    }

    private Board parseBoard(JSONObject jsonObject) {
        Board b = new Board();
        loadShips(b, jsonObject);
        return b;
    }

    private void loadShips(Board b, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("ships");
        for (Object json: jsonArray) {
            JSONObject nextShip = (JSONObject) json;
            loadShip(b, nextShip);
        }
        /* filter dead ships
        for (Ship s: jsonArray) {
            if (s.isSunk()) {
            // search and remove this ship
            jsonArray.remove(jsonArray.indexOf(s));
            }
        }
         */
    }

    // loadBoardArray()
    // this needs to keep the values in the 2d array so we dont need to recreate it
    // alternative is to write and filter ships -> create boardArray from that list (but then you lose the squares
    //     that have already guessed)

    private void loadShip(Board b, JSONObject jsonObject) {
        int size = jsonObject.getInt("size");
        int coordX = jsonObject.getInt("coordX");
        int coordY = jsonObject.getInt("coordY");
        int dir = jsonObject.getInt("dir");


        b.addShip(size, coordX, coordY, dir);
    }
}
