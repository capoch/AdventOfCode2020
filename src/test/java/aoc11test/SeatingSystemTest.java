package test.java.aoc11test;

import main.java.aoc11.SeatingSystem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class SeatingSystemTest {

    @Test
    public void TestPartOne() throws IOException {
        SeatingSystem.getSeatPlan("resources/test11.txt");
        Assertions.assertEquals(37, SeatingSystem.stableStateCount(false));
    }

    @Test
    public void TestPartTwo() throws IOException {
        SeatingSystem.getSeatPlan("resources/test11.txt");
        Assertions.assertEquals(26, SeatingSystem.stableStateCount(true));
    }
}
