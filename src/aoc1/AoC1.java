package aoc1;
import support.ReadFile.ReadFile;
import support.WriteFile.WriteFile;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class AoC1 {
    public static void main(String[] args) {
        ReadFile rf = new ReadFile("aoc1");
        rf.readLines(args[0]);
        // only select the first and second number and discard the rest and any other numbers
        Pattern p = Pattern.compile("([0-9]+)");

        ArrayList<String> output = new ArrayList<String>();
        for (String line : rf.getLines()) {
            StringBuilder outputtemp = new StringBuilder();
            for (String line2 : line.split("")) {
                if (line2.matches("[0-9]")) {
                    outputtemp.append(line2);
                }
                if (outputtemp.length() == 2) {
                    output.add(outputtemp.toString());
                    break;
                }
                System.out.println(outputtemp);
            }
            if (outputtemp.length() == 1) {
                output.add(outputtemp.toString());
            }
        }
        WriteFile wf = new WriteFile("aoc1");
        wf.write(args[1], output.toString(), true);
    }
}
