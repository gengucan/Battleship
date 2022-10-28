package persistence;

import model.Board;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Citation: JsonSerializationDemo

public class JsonWrite {
    private PrintWriter writer;
    private String file; // create two files, one for each?

    public JsonWrite(String file) {
        this.file = file;
    }

    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(file));
    }


    public void write(Board b) { // need to call this twice (once per board)
        JSONObject json1 = b.toJson(); // save board array to an array
        JSONObject json2 = b.toJson(); //save ships array to another array

        // .toJson() is a fn that translates to JSON including context by overriding at the class level

        saveFile(json1.toString(4));
        saveFile(json2.toString(4));

        // call this on both boards when save() is called
    }

    //EFFECTS: Closes writer
    public void close() {
        writer.close();
    }

    //EFFECTS: Writes string to file
    public void saveFile(String json) {
        writer.print(json);
    }
}

