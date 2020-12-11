package main.java.aoc11;

import main.java.shared.InputParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SeatingSystem {
    public static List<List<String>> seatPlan = new ArrayList<>(new ArrayList<>());
    public static boolean stable = false;

    static public void getSeatPlan(String fileLocation) throws IOException {
        List<List<String>> mySeatPlan = new ArrayList<>();
        InputParser.parseReport(fileLocation).stream()
                .map(line -> line.codePoints()
                        .mapToObj(ch -> Character.toString((char) ch))
                        .collect(Collectors.toList()))
                .forEach(mySeatPlan::add);
        seatPlan = new ArrayList<>(mySeatPlan);
    }

    public static int stableStateCount() {
        while(!SeatingSystem.stable) { SeatingSystem.transform();}
        return SeatingSystem.seatPlan.stream()
                .mapToInt(line -> (int)line.stream().filter(seat -> seat.equals("#")).count())
                .sum();
    }

    public static int stableStateCountLineOfSight() {
        while(!SeatingSystem.stable) {
            showSeats();
            System.out.println("=========");
            SeatingSystem.transformLineOfSight();}
        return SeatingSystem.seatPlan.stream()
                .mapToInt(line -> (int)line.stream().filter(seat -> seat.equals("#")).count())
                .sum();
    }

    public static void transform() {
        List<List<Boolean>> transformationMatrix = new ArrayList<>();
        List<List<String>> newSeatPlan = new ArrayList<>();
        for(int j = 0; j < seatPlan.size(); j++) {
            List<Boolean> line = new ArrayList<>();
            for(int i = 0; i < seatPlan.get(j).size(); i++) {
                line.add(willChange(i, j));
            }
            transformationMatrix.add(line);
        }
        for(int j = 0; j < transformationMatrix.size(); j++) {
            List<String> line = new ArrayList<>();
            for(int i = 0; i < transformationMatrix.get(j).size(); i++) {
                line.add(getNewSeat(i,j, transformationMatrix.get(j).get(i)));
            }
            newSeatPlan.add(line);
        }
        if(compareSeatPlans(seatPlan, newSeatPlan)) {
            stable = true;
        }
        seatPlan = newSeatPlan;
    }

    public static void transformLineOfSight() {
        List<List<Boolean>> transformationMatrix = new ArrayList<>();
        List<List<String>> newSeatPlan = new ArrayList<>();
        for(int j = 0; j < seatPlan.size(); j++) {
            List<Boolean> line = new ArrayList<>();
            for(int i = 0; i < seatPlan.get(j).size(); i++) {
                line.add(willChangeLineOfSight(i, j));
            }
            transformationMatrix.add(line);
        }
        for(int j = 0; j < transformationMatrix.size(); j++) {
            List<String> line = new ArrayList<>();
            for(int i = 0; i < transformationMatrix.get(j).size(); i++) {
                line.add(getNewSeat(i,j, transformationMatrix.get(j).get(i)));
            }
            newSeatPlan.add(line);
        }
        if(seatPlan.equals(newSeatPlan)) {
            stable = true;
        }
        seatPlan = newSeatPlan;
    }

    private static boolean willChange(int x, int y) {
        return getSeat(x, y).equals("L") && (short) getNeighbours(x,y).stream().filter(n -> n.equals("L")).count() == getNeighbourCount(x,y) ||
                getSeat(x, y).equals("#") && getNeighbours(x,y).stream().filter(n -> n.equals("#")).count() >= 4;
    }

    private static boolean willChangeLineOfSight(int x, int y) {
        return getSeat(x, y).equals("L") && getLineOfSightOccupiedSeatCount(x, y).stream().filter(seat -> seat.equals("L")).count() == getLineOfSightOccupiedSeatCount(x, y).stream().count() ||
                getSeat(x, y).equals("#") && getLineOfSightOccupiedSeatCount(x, y).stream().filter(seat -> seat.equals("#")).count() >= 5;
    }

    private static short getNeighbourCount(int x, int y) {
        return (short) getNeighbours(x, y).stream().filter(n -> !n.equals(".")).count();
    }

    private static String getNewSeat(int x, int y, boolean changes) {
        if(getSeat(x,y).equals("L") && changes) {
            return "#";
        } else if(getSeat(x,y).equals("#") && changes) {
            return "L";
        } else {
            return getSeat(x,y);
        }
    }

    public static void showSeats() {
        seatPlan.stream()
                .map(SeatingSystem::showSeatRow)
                .forEach(System.out::println);
    }

    private static String showSeatRow(List<String> row)  {
        StringBuilder sb = new StringBuilder();
        row.stream().forEach(sb::append);
        return sb.toString();
    }

    private static String getSeat(int x, int y) {
        return seatPlan.get(y).get(x);
    }

    private static List<String> getNeighbours(int x, int y) {
        List<String> neighbours = new ArrayList<>();
        for(int i = -1; i <= 1; i++) {
            for(int j = -1; j <= 1; j++) {
                if(!(i == 0 && j == 0) && x + i >= 0 && x + i < seatPlan.get(0).size() && y + j >= 0 && y + j < seatPlan.size()) {
                    neighbours.add(getSeat(x+i, y + j));
                }
            }
        }
        return neighbours;
    }

    private static List<String> getLineOfSightOccupiedSeatCount(int x, int y) {
        List<String> neighbours = new ArrayList<>();
        int dy = 1;
        int dx = 1;

        while(y + dy < seatPlan.size()) {
            String seat = seatPlan.get(y + dy).get(x);
            if(!seat.equals(".")) {
                neighbours.add(seat);
                break;
            }
            dy += 1;
        }
        dy = 1;
        while(y - dy >= 0) {
            String seat = seatPlan.get(y - dy).get(x);
            if(!seat.equals(".")) {
                neighbours.add(seat);
                break;
            }
            dy += 1;
        }
        dy = 1;
        while(x + dx < seatPlan.get(0).size()) {
            String seat = seatPlan.get(y).get(x + dx);
            if(!seat.equals(".")) {
                neighbours.add(seat);
                break;
            }
            dx += 1;
        }
        dx = 1;
        while(x - dx >= 0) {
            String seat = seatPlan.get(y).get(x - dx);
            if(!seat.equals(".")) {
                neighbours.add(seat);
                break;
            }
            dx += 1;
        }
        dx = 1;
        while(y + dy < seatPlan.size() && x + dx < seatPlan.get(0).size()) {
            String seat = seatPlan.get(y + dy).get(x + dx);
            if(!seat.equals(".")) {
                neighbours.add(seat);
                break;
            }
            dy += 1;
            dx += 1;
        }
        dx = 1;
        dy = 1;
        while(y - dy >= 0 && x + dx < seatPlan.get(0).size()) {
            String seat = seatPlan.get(y - dy).get(x + dx);
            if(!seat.equals(".")) {
                neighbours.add(seat);
                break;
            }
            dy += 1;
            dx += 1;
        }
        dx = 1;
        dy = 1;
        while(y + dy < seatPlan.size() && x - dx >= 0) {
            String seat = seatPlan.get(y + dy).get(x - dx);
            if(!seat.equals(".")) {
                neighbours.add(seat);
                break;
            }
            dy += 1;
            dx += 1;
        }
        dx = 1;
        dy = 1;
        while(y - dy >= 0 && x - dx >= 0) {
            String seat = seatPlan.get(y - dy).get(x - dx);
            if(!seat.equals(".")) {
                neighbours.add(seat);
                break;
            }
            dy += 1;
            dx += 1;
        }

        return neighbours;
    }

    public static void main(String[] args) throws IOException {
        //getSeatPlan("resources/input11.txt");
        //System.out.println("Part One: " + stableStateCount());
        getSeatPlan("resources/input11.txt");
        System.out.println("Part Two: " + stableStateCountLineOfSight());
    }
}