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
        ArrayList<String> output = replaceAll(rf.getLines(), regex);
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
        String lookbehind = "(?<=(" + regex + "))";
        ArrayList<String> output = new ArrayList<String>();
        ArrayList<String> outputla = new ArrayList<String>();
        ArrayList<String> outputlb = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        Pattern pla = Pattern.compile(lookahead);
        Pattern plb = Pattern.compile(lookbehind);
        Matcher m;
        Matcher n;
        for (String line : input) {
            m = pla.matcher(line);
            while (m.find()) {
                sb.append(m.group(1));
            }
            outputla.add(sb.toString());
            sb.setLength(0);
        }
        for (String line : input) {
            n = plb.matcher(line);
            while (n.find()) {
                sb.append(n.group(1));
            }
            outputlb.add(sb.toString());
            sb.setLength(0);
        }
        for (int i = 0; i < outputla.size(); i++) {
            if (outputla.get(i).equals(outputlb.get(i))) {
                output.add(outputla.get(i));
            } else if (outputla.get(i).length() > outputlb.get(i).length()) {
                output.add(outputla.get(i));
            } else {
                output.add(outputlb.get(i));
            }
        }
        for (String line : output) {
            if (line.length() == 1) {
                int index = output.indexOf(line);
                String temp = line;
                line += temp;
                output.set(index, line);
            } else if (line.length() > 2) {
                int index = output.indexOf(line);
                String temp = line;
                temp = line.charAt(0) + line.substring(line.length()-1);
                output.set(index, temp);
            }
        }
        return output;
    }
}
