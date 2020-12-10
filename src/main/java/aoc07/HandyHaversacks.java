package main.java.aoc07;

import main.java.shared.InputParser;
import sun.lwawt.macosx.CSystemTray;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class HandyHaversacks {
    static Map<String, List<String>> parsedBagList = new HashMap<>();
    static String target = "";

    static public void createMap(String fileLocation) {
        List<String> input = new ArrayList<>();
        try{
            input = InputParser.parseReport(fileLocation);
        } catch(IOException e) {
            System.out.println("FFFAIIIIILLL");
        }
        input = input.stream()
                .map(line -> line = line.replaceAll(" bags contain ", ":"))
                .map(line -> line = line.replaceAll(" bags", ""))
                .map(line -> line = line.replaceAll(" bag", ""))
                .map(line -> line = line.replaceAll(" bags", ""))
                .map(line -> line = line.replaceAll(" bag", ""))
                .map(line -> line = line.replaceAll("\\.", ""))
                .map(line -> line = line.replaceAll("[0-9]{1}[ ]", ""))
                .collect(Collectors.toList());
        Map<String, List<String>> result = new HashMap<>();
        input.forEach(record -> {
            String[] rule = record.split(":");
            result.put(rule[0], Arrays.asList(rule[1].split(", ")));
        });
        parsedBagList = result;
    }

    static public void createFullMap(String fileLocation) {
        List<String> input = new ArrayList<>();
        try{
            input = InputParser.parseReport(fileLocation);
        } catch(IOException e) {
            System.out.println("FFFAIIIIILLL");
        }
        System.out.println(input.get(0));
        input = input.stream()
                .map(line -> line = line.replaceAll(" bags contain ", ":"))
                .map(line -> line = line.replaceAll(" bags,", ","))
                .map(line -> line = line.replaceAll(" bag,", ","))
                //.map(line -> line = line.replaceAll(" bags", ","))
                //.map(line -> line = line.replaceAll(" bag", ","))
                .map(line -> line = line.replaceAll("\\.", ""))
                .map(line -> line =  line.replaceAll(", ", ","))
                .map(line -> line = line.replaceAll("^([\\d{1}])\\s", "$1"))
                .collect(Collectors.toList());
        System.out.println(input.get(0));
        Map<String, List<String>> result = new HashMap<>();
        input.forEach(record -> {
            String[] rule = record.split(":");
            result.put(rule[0], Arrays.asList(rule[1].split(", ")));
        });
        System.out.println(result.get(result));
        parsedBagList = result;
    }

    static public int countEntry(String listItem) {
        if(listItem.equals(target)) {
            return 1;
        } else if(listItem.equals("no other")) {
            return 0;
        } else {
            return countWaysForEntry(listItem);
        }
    }

    static public int countWaysForEntry(String entry) {
        return parsedBagList.get(entry).stream()
                .mapToInt(HandyHaversacks::countEntry)
                .sum();
    }

    static public long countWaysToTarget(String myTarget) {
        target = myTarget;
        return HandyHaversacks.parsedBagList.keySet().stream()
                //.filter(key -> !key.equals(target))
                .map(HandyHaversacks::countWaysForEntry)
                .filter(t -> t > 0)
                .count();
    }

    public static void main(String[] args) {
        HandyHaversacks.createMap("resources/input07.txt");
        System.out.println("Part one: " + HandyHaversacks.countWaysToTarget("shiny gold"));
        HandyHaversacks.createFullMap("resources/input07.txt");

    }
}
