package main.java.aoc05;

import main.java.shared.InputParser;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BinaryBoarding {
    static int rowNumber(String binaryRowInput) {
        return Integer.parseInt(binaryRowInput.replaceAll("F", "0").replaceAll("B", "1"), 2);
    }

    static int colNumber(String binaryColInput) {
        return Integer.parseInt(binaryColInput.replaceAll("L", "0").replaceAll("R", "1"), 2);
    }

    static int getUnitSeatId(String seatCode) {
        return 8 * rowNumber(seatCode.substring(0,7)) + colNumber(seatCode.substring(7,10));
    }

    public static void main(String[] args) {
        try {
            List<String> data = InputParser.parseReport("resources/input05.txt");
            System.out.println("Frist part: " +
                    Collections.max(data.stream()
                            .map(BinaryBoarding::getUnitSeatId)
                            .collect(Collectors.toList())
                    )
            );
            List<Integer> thelist = data.stream().map(BinaryBoarding::getUnitSeatId).sorted().collect(Collectors.toList());
            for(int i = 0; i < thelist.size() - 1; i++) {
                if(thelist.get(i) != thelist.get(i + 1) - 1) {
                    System.out.println("Second part " + String.valueOf(thelist.get(i) + 1));
                }
            }
        } catch(IOException e) {
            System.out.println("Parsing failed");
        }
    }
}
