package test.java.aoc10test;

import main.java.aoc09.EncodingError;
import main.java.aoc10.AdapterArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class AdapterArrayTest {
    static AdapterArray adapterArray = new AdapterArray();

    @Test
    public void TestPartOne_One() throws IOException {
        adapterArray.parseAdapterList("resources/test10.txt");
        Assertions.assertEquals(35, AdapterArray.getAdapterDifferenceValue());
    }

    @Test
    public void TestPartOne_Two() throws IOException{
        adapterArray.parseAdapterList("resources/test10_02.txt");
        Assertions.assertEquals(220, AdapterArray.getAdapterDifferenceValue());
    }

    @Test
    public void TestPartTwo_One() throws IOException {
        adapterArray.parseAdapterList("resources/test10.txt");
        Assertions.assertEquals(8, AdapterArray.countPossibleAdapterArrangements());
    }

    @Test
    public void TestPartTwo_Two() throws IOException{
        adapterArray.parseAdapterList("resources/test10_02.txt");
        Assertions.assertEquals(19208, AdapterArray.countPossibleAdapterArrangements());
    }
}
