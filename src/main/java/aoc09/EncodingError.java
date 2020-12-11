package main.java.aoc09;

import main.java.shared.InputParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EncodingError {
    static List<String> data = new ArrayList<>();
    static Integer preamble = null;

    static public void loadData(int myPreamble, String fileLocation) throws IOException {
        data = InputParser.parseReport(fileLocation);
        preamble = myPreamble;
    }

    static boolean checkValidity(int n) {
        if(n >= preamble && n < data.size()) {
            for(int i = n - preamble; i < n - 1; i ++) {
                for(int j = n - preamble + 1; j < n + 1; j++) {
                    if(Integer.valueOf(data.get(n)) == Integer.valueOf(data.get(i)) + Integer.valueOf(data.get(j))) {
                        return true;
                    }
                }
            }
            return false;

        } else {
            System.out.println("Error: out of range");
            return false;
        }

    }

    static public int[] findInvalidNumber() {
        for (int i = preamble; i < data.size(); i++) {
            if(!EncodingError.checkValidity(i)) {
                return new int[]{i, Integer.valueOf(data.get(i))};
            }
        }
        return new int[]{0, 0};
    }

    static public int findEncryptionWeakness() {
        int[] result = findInvalidNumber();
        int targetSum = result[1];
        int maxIndex = result[0];
        for(int i = 0; i < maxIndex; i++) {
            for (int j = i; j < maxIndex; j++) {
                if(data.subList(i, j).stream()
                        .mapToInt(Integer::valueOf)
                        .sum() == targetSum) {
                    List<Integer> intList = data.subList(i, j).stream().map(Integer::valueOf).collect(Collectors.toList());
                    return Collections.min(intList) + Collections.max(intList);
                }
            }
        }
        return 0;
    }




    public static void main(String[] args) throws IOException {
        EncodingError.loadData(25, "resources/input09.txt");
        System.out.println("Part One: " + EncodingError.findInvalidNumber()[1]);
        System.out.println("Part Two: " + EncodingError.findEncryptionWeakness());
    }
}
