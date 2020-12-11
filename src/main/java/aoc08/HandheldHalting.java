package main.java.aoc08;

import main.java.shared.GameConsole;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HandheldHalting {
    static List<Integer> getActionLocations(GameConsole console, String action) {
        List<Integer> actionLocations = new ArrayList<>();
        List<String> program = console.getProgram();
        for(int i = 0; i < program.size(); i++) {
            if(program.get(i).split(" ")[0].equals(action)) { actionLocations.add(i); }
        }
        return actionLocations;
    }

    public static Integer switchJmpAndNopTillRunning(GameConsole console) {
        List<String> cachedProgram = new ArrayList<>(console.getProgram());
        List<Integer> jmpLocations = HandheldHalting.getActionLocations(console, "jmp");
        List<Integer> nopLocations = HandheldHalting.getActionLocations(console, "nop");

        for(int i = 0; i < jmpLocations.size(); i++) {
            List<String> program = console.getProgram();
            int targetIndex = jmpLocations.get(i);
            program.set(targetIndex, (program.get(targetIndex)).replaceAll("jmp", "nop"));
            if(console.isDebug()) {
                System.out.println("JMP switched on " + targetIndex);}
            if(console.run() == 0) {
                return console.getAccumulator();
            }
            console.setProgram(new ArrayList<>(cachedProgram));
        }

        for(int i = 0; i < nopLocations.size(); i++) {
            List<String> program = console.getProgram();
            int targetIndex = jmpLocations.get(i);
            program.set(targetIndex, (program.get(targetIndex)).replaceAll("nop", "jmp"));
            if(console.isDebug()) {
                System.out.println("NOP switched on " + targetIndex);}
            if(console.run() == 0) {
                return console.getAccumulator();
            }
            console.setProgram(new ArrayList<>(cachedProgram));
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        GameConsole console = new GameConsole();
        //console.toggleDebug();
        console.loadProgram("resources/input08.txt");
        console.run();
        System.out.println("Part One: " + console.getAccumulator());
        System.out.println("Part Two: " + HandheldHalting.switchJmpAndNopTillRunning(console));

    }
}
