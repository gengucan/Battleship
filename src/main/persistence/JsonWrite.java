package persistence;

import model.Board;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Citation: JsonSerializationDemo

// Represents a writer that writes JSON representation of data to file
public class JsonWrite {
    private PrintWriter writer;
    private String file;

    //EFFECTS: Creates a writer
    public JsonWrite(String file) {
        this.file = file;
    }

    //EFFECTS: opens writer and throws FileNotFoundException if file can't be found
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(file));
    }

    //EFFECTS: writes JSON representation of data to file
    public void write(Board b) {
        JSONObject json1 = b.toJson();

        saveFile(json1.toString(4));
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

