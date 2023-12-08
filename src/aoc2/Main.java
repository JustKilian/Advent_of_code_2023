package aoc2;

import support.ReadFile.ReadFile;
import support.WriteFile.WriteFile;

import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ReadFile rf = new ReadFile("rf");
        rf.readLines(args[0]);
        ArrayList<Integer> max = new ArrayList<Integer>(Arrays.asList(Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4])));
        Integer numberOfMatches = 0;
        for (String line : rf.getLines()) {
            Map<Integer, ArrayList<Map>> map = parse(line);
            if (analyze(map, max) == null) {
                continue;
            }
            numberOfMatches += getNumberOfMatches(map.values().iterator().next());
        }
        WriteFile wf = new WriteFile("wf");
        wf.write(args[1], numberOfMatches.toString(), true);
    }

    public static Integer getNumberOfMatches(ArrayList<Map> matches){
        System.out.println(matches);
        return matches.size();
    }

    public static Map<Integer, ArrayList<Map>> analyze(Map<Integer, ArrayList<Map>> map, ArrayList<Integer> max) {
        Map<Integer, ArrayList<Map>> wrongoutput = new HashMap<Integer, ArrayList<Map>>();
        Map<Integer, ArrayList<Map>> output = map;
        one:
        for (ArrayList<Map> entry : map.values()) {
            two:
            for (Map match : entry) {
                if (match.get("red") != null && (Integer) match.get("red") > max.get(0)) {
                    wrongoutput.put(map.keySet().iterator().next(), entry);
                    break one;
                }
                if (match.get("green") != null && (Integer) match.get("green") > max.get(1)) {
                    wrongoutput.put(map.keySet().iterator().next(), entry);
                    break one;
                }
                if (match.get("blue") != null && (Integer) match.get("blue") > max.get(2)) {
                    wrongoutput.put(map.keySet().iterator().next(), entry);
                    break one;
                }
            }
        }
        for (Integer key : wrongoutput.keySet()) {
            map.remove(key);
            return null;
        }
        return map;
    }

    public static Map<Integer, ArrayList<Map>> parse(String line) {
        Map<Integer, ArrayList<Map>> map = new HashMap<Integer, ArrayList<Map>>();
        line = line.replace("Game ", "");
        ArrayList<String> parts = new ArrayList<>(Arrays.asList(line.split(": ")));
        ArrayList<String> matches = new ArrayList<>(Arrays.asList(parts.get(1).split("; ")));
        ArrayList<Map> matchMaps = new ArrayList<Map>();
        for (String match : matches) {
            Map<String, Integer> matchMap = new HashMap<String, Integer>();
            ArrayList<String> matchParts = new ArrayList<>(Arrays.asList(match.split(", ")));
            for (String matchPart : matchParts) {
                if (matchPart.contains("red")) {
                    matchMap.put("red", Integer.parseInt(matchPart.replace(" red", "")));
                } else if (matchPart.contains("blue")) {
                    matchMap.put("blue", Integer.parseInt(matchPart.replace(" blue", "")));
                } else if (matchPart.contains("green")) {
                    matchMap.put("green", Integer.parseInt(matchPart.replace(" green", "")));
                }
            }
            matchMaps.add(matchMap);
        }
        map.put(Integer.parseInt(parts.get(0)), matchMaps);

        return map;
    }
}
