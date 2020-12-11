package main.java.aoc10;

import main.java.shared.InputParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AdapterArray {
    static List<Integer> adapters = new ArrayList<>();

    static public void parseAdapterList(String fileLocation) throws IOException {
        List<Integer> parsedInput = InputParser.parseReport(fileLocation)
                .stream().map(Integer::valueOf).collect(Collectors.toList());
        parsedInput.add(0);
        parsedInput.add(Collections.max(parsedInput) + 3);
        adapters = parsedInput;
    }

    private static List<Integer> getDeltaLists() {
        List<Integer> sortedAdapters = adapters.stream().sorted().collect(Collectors.toList());
        List<Integer> adapterDelta = new ArrayList();
        for(int i = 0; i < sortedAdapters.size() - 1; i++) {
            adapterDelta.add(sortedAdapters.get(i + 1) - sortedAdapters.get(i));
        }
        return adapterDelta;
    }

    private static List<Integer> getCumulatedDeltaList(List<Integer> deltaList) {
        List<Integer> cumDelta = new ArrayList<>();
        for(int i=0; i < deltaList.size(); i++){
            if(deltaList.get(i) == 3) {
                cumDelta.add(0);
            } else {
                if(i == 0 || cumDelta.get(cumDelta.size()-1) == 0) {
                    cumDelta.add(1);
                } else {
                    cumDelta.set(cumDelta.size()-1, cumDelta.get(cumDelta.size()-1) + 1);
                }
            }
        }
        return cumDelta;
    }

    private static int getPossibilities(int size) {
        switch (size) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 4;
            case 4:
                return 7;
            default:
                return 1;
        }
    }


    public static int getAdapterDifferenceValue() {
        List<Integer> unsortedAdapters = AdapterArray.adapters.stream().collect(Collectors.toList());
        List<Integer> sortedAdapters = unsortedAdapters.stream().sorted().collect(Collectors.toList());
        System.out.println(sortedAdapters);
        int Diff1Counter = 0;
        int Diff3Counter = 0;
        for(int i = 0; i < sortedAdapters.size() - 1; i++) {
            if(sortedAdapters.get(i+1) - sortedAdapters.get(i) == 1) {
                Diff1Counter += 1;
            } else if(sortedAdapters.get(i + 1) - sortedAdapters.get(i) == 3) {
                Diff3Counter += 1;
            }
        }
        System.out.println(Diff1Counter);
        System.out.println(Diff3Counter);
        return Diff1Counter * Diff3Counter;
    }

    public static long countPossibleAdapterArrangements() {
        return getCumulatedDeltaList(getDeltaLists()).stream()
                .filter(ent -> ent != 0)
                .map(num -> getPossibilities(num))
                .map(in -> (long)in)
                .reduce(1L, (a, b) -> a * b);
    }
    public static void main(String[] args) throws IOException {
        parseAdapterList("resources/input10.txt");
        System.out.println("Part One: " + getAdapterDifferenceValue());
        System.out.println("Second Part " + countPossibleAdapterArrangements());

    }
}
