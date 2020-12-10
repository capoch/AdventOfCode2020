package test.java.aoc07test;

import jdk.nashorn.internal.ir.annotations.Ignore;
import main.java.aoc07.HandyHaversacks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class HandyHaversacksTest {
    static List<String> data = new ArrayList<>();

    @BeforeAll
    public static void setup() {
        HandyHaversacks.createMap("resources/test07.txt");
    }

    @Test
    public void TestPartOne() {
        Assertions.assertEquals(HandyHaversacks.countWaysToTarget("shiny gold"), 4);

    }

    @Test
    public void TestPartTwo() {
        Assertions.assertEquals(HandyHaversacks.countWaysToTarget("shiny gold"), 32);
    }

    @Test
    public void TestPartTwoTwo() {
        HandyHaversacks.createMap("resources/test0702.txt");
        Assertions.assertEquals(HandyHaversacks.countWaysToTarget("shiny gold"), 126);
    }
}
