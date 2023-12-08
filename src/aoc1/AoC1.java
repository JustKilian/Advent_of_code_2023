package aoc1;
import support.ReadFile.ReadFile;
import support.WriteFile.WriteFile;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AoC1 {
    public static void main(String[] args) {
        ReadFile rf = new ReadFile("aoc1");
        rf.readLines(args[0]);
        // only select the first and second number and discard the rest and any other numbers
        String regextask2 = "[1-9]|one|two|three|four|five|six|seven|eight|nine";
        //String regextask1 = "[1-9]";
        ArrayList<String> output = replaceAll(rf.getLines(), regextask2);
        output.add(Integer.toString(sum(output)));
        WriteFile wf = new WriteFile("aoc1");
        wf.write(args[1], output.toString(), true);
    }

    public static int sum(ArrayList<String> input) {
        int sum = 0;
        for (String line : input) {
            sum += Integer.parseInt(line);
        }
        return sum;
    }

    public static ArrayList<String> replaceAll(ArrayList<String> input, String regex) {
        String lookahead = "(?=(" + regex + "))";
        ArrayList<String> output = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        Pattern pla = Pattern.compile(lookahead);
        Matcher m;
        for (String line : input) {
            m = pla.matcher(line);
            while (m.find()) {
                sb.append(m.group(1));
            }
            output.add(sb.toString());
            sb.setLength(0);
        }
        for (String line : output) {
            int index = output.indexOf(line);
            line = line.replaceAll("one", "1");
            line = line.replaceAll("two", "2");
            line = line.replaceAll("three", "3");
            line = line.replaceAll("four", "4");
            line = line.replaceAll("five", "5");
            line = line.replaceAll("six", "6");
            line = line.replaceAll("seven", "7");
            line = line.replaceAll("eight", "8");
            line = line.replaceAll("nine", "9");
            output.set(index, line);
        }
        for (String line : output) {
            if (line.length() == 1) {
                int index = output.indexOf(line);
                String temp = line;
                line += temp;
                output.set(index, line);
            } else if (line.length() > 2) {
                int index = output.indexOf(line);
                String temp;
                temp = line.charAt(0) + line.substring(line.length()-1);
                output.set(index, temp);
            }
        }
        return output;
    }
}
