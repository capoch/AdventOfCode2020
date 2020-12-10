package test.java.aoc06test;

import main.java.aoc06.CustomCustoms;
import main.java.shared.InputParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomCustomsTest {
    static List<String> data = new ArrayList<>();

    @BeforeAll
    public static void setup() {
        try {
            data = InputParser.parseReport("resources/test06.txt");
        } catch(IOException e) {}
    }

    @Test
    public void TestPartOne() {
        Assertions.assertEquals(CustomCustoms.evaluateForms(data), 11);

    }

    @Test
    public void TestPartTwo() {
        Assertions.assertEquals(CustomCustoms.evaluateConjForms(data), 6);
    }
}
