package support.WriteFile;

import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors

public class WriteFile {
    // begin attributes
    private final String name;
    private String content;
    private String filename;
    // end attributes
    
    // begin methods
    public WriteFile(String name) {
        this.name = name;
    }
    public boolean write(String filename, String content) {
        this.filename = filename;
        this.content = content;
        try {
            File myObj = new File(this.filename);
            if (myObj.createNewFile()) {
                java.io.FileWriter myWriter = new java.io.FileWriter(this.filename);
                myWriter.write(this.content);
                myWriter.close();
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean write(String filename, String content, Boolean overwrite){
        this.filename = filename;
        this.content = content;
        try {
            File myObj = new File(this.filename);
            if (myObj.createNewFile() || overwrite) {
                java.io.FileWriter myWriter = new java.io.FileWriter(this.filename);
                myWriter.write(this.content);
                myWriter.close();
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
