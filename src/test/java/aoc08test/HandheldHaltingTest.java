package test.java.aoc08test;

import main.java.aoc08.HandheldHalting;
import main.java.shared.GameConsole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class HandheldHaltingTest {
    static GameConsole console = new GameConsole();
    @BeforeAll
    public static void setup() throws IOException {
        console.loadProgram("resources/test08.txt");
        //console.toggleDebug();
    }

    @Test
    public void TestPartOne() {
        Assertions.assertEquals(2, console.run());
        Assertions.assertEquals(5, console.getAccumulator());
    }

    @Test
    public void TestPartTwo() {
        Assertions.assertEquals(8, HandheldHalting.switchJmpAndNopTillRunning(console));
    }
}
