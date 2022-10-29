package persistence;


import model.Board;
import model.Ship;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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
        Board b = new Board(); // this is what makes us print a new board rn
        loadShips(b, jsonObject);
        loadBoardArray(b, jsonObject);

        b.setSunken(jsonObject.getInt("sunken"));

        return b;
    }

    private void loadShips(Board b, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("ships");
        for (Object json: jsonArray) {
            JSONObject nextShip = (JSONObject) json;
            loadShip(b, nextShip);
        }
    }

    private void loadShip(Board b, JSONObject jsonObject) {
        int size = jsonObject.getInt("size");
        int coordX = jsonObject.getInt("coordX");
        int coordY = jsonObject.getInt("coordY");
        int dir = jsonObject.getInt("dir");

        JSONArray savedHits = jsonObject.getJSONArray("hitTiles");
        boolean[] loadHits = new boolean[size];

        for (int i = 0; i < size; i++) {
            loadHits[i] = savedHits.getBoolean(i);
        }


        b.addShip(size, coordX, coordY, dir, loadHits);

// for testing!!
//        for (int i = 0; i < b.getShips().size(); i++) {
//            System.out.print(b.getShips().get(i).isSunk());
//        }
//        System.out.println("");
    }



    private void loadBoardArray(Board b, JSONObject jsonObject) {
        int[][] boardArray = new int[8][8];

        JSONArray savedBoardArray = jsonObject.getJSONArray("boardArray");

        for (int i = 0; i < 8; i++) {
            JSONArray innerArray = savedBoardArray.getJSONArray(i);
            for (int j = 0; j < 8; j++) {
                boardArray[i][j] = innerArray.getInt(j);
            }
        }
        b.setBoardArray(boardArray);
        //System.out.println(jsonObject.getJSONArray("boardArray"));
    }
}
