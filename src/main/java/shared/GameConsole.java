package main.java.shared;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameConsole {
    boolean running;

    public boolean isDebug() {
        return debug;
    }

    boolean debug = false;

    public void setProgram(List<String> program) {
        this.program = program;
    }

    public void toggleDebug() {
        debug = !debug;
    }

    List<String> program = new ArrayList<String>();

    public int getAccumulator() {
        return accumulator;
    }

    int accumulator = 0;
    int programExecution = 0;

    public void loadProgram(String programLocation) throws IOException {
        program = InputParser.parseReport(programLocation);
    }

    public List<String> getProgram() {
        return program;
    }

    public void executeProgramInstruction(String unparsedInstruction) {
        String[] instruction = unparsedInstruction.split(" ");
        switch (instruction[0]) {
            case "acc":
                accumulator += Integer.valueOf(instruction[1]);
                programExecution += 1;
                return;
            case "jmp":
                programExecution += Integer.valueOf(instruction[1]);
                return;
            case "nop":
                programExecution += 1;
                return;
            default:
                return;
        }
    }

    public int run() {
        if(debug) {System.out.println("Starting Program execution");}
        accumulator = 0;
        programExecution = 0;
        running = true;
        List<Integer> executedLines = new ArrayList<>();
        while(running) {
            if(debug) {
                System.out.println("Program Execution (" + programExecution + "/" + (program.size() - 1) + ") Step " + program.get(programExecution) + ". Accumulator: " + accumulator);
            }
            if(executedLines.contains(programExecution)) {
                if(debug) {
                    System.out.println("Error Infinite Loop. Accumulator value : " + accumulator);
                }
                running = false;
                return 2;
            }

            executedLines.add(programExecution);
            executeProgramInstruction(program.get(programExecution));

            if(programExecution > program.size() || programExecution < 0) {
                running = false;
                if(debug) {
                    System.out.println("Error Program out of bounds. Accumulator: " + accumulator);
                }
                return 1;
            }
            if(programExecution == program.size()) {
                running = false;
                System.out.println("Program terminated successfully. Accumulator: " + accumulator);
                return 0;
            }


        }
        return -1;
    }

}
