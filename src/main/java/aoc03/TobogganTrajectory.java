package main.java.aoc03;

import main.java.shared.InputParser;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TobogganTrajectory {
    static char fetchCoordinates(int x, int y, List<String> map) {
        String line = map.get(y);
        return line.charAt(x % line.length());
    }



    public static void main(String[] args) {
        try {
            List<String> data = InputParser.parseReport("resources/input03.txt");
            List<List<Integer>> paths = Arrays.asList(Arrays.asList(1,1), Arrays.asList(3,1), Arrays.asList(5,1), Arrays.asList(7,1), Arrays.asList(1,2));
            int result = 0;
            int x = 0;
            for(int i = 0; i <= data.size() - 1; i++) {
                if(TobogganTrajectory.fetchCoordinates(x,i,data) =='#') {
                    result += 1;
                }
                x += 3;
            }
            System.out.println("First part " + result);

            long result2 = 1;
            for(List<Integer> path : paths) {
                int pathCount = 0;
                int x2 = 0;
                for(int i = 0; i <= data.size() - 1; i += path.get(1)) {
                    if(TobogganTrajectory.fetchCoordinates(x2,i,data) =='#') {
                        pathCount += 1;
                    }
                    x2 += path.get(0);
                }
                result2 *= pathCount;
            }
            System.out.println("Second part " + result2);

        } catch(IOException e) {
            System.out.println("FAAAAIIIILLLL");
        }
    }
}
