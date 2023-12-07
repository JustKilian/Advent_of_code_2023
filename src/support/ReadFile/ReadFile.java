package support.ReadFile;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

public class ReadFile {
    // begin attributes
    private final String name;
    private final ArrayList<String> lines = new ArrayList<String>();
    // end attributes

    // begin methods
    public ReadFile(String name) {
        this.name = name;
    }
    public ArrayList<String> readLines(String filename) {
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                this.lines.add(myReader.nextLine());
            }
            myReader.close();
            return this.lines;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> getLines() {
        return this.lines;
    }
    // end methods
}
