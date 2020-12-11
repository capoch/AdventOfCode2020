package test.java.aoc09test;

import main.java.aoc09.EncodingError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class EncodingErrorTest {
    static EncodingError encodingError = new EncodingError();

    @BeforeAll
    public static void setup() throws IOException {
        encodingError.loadData(5, "resources/test09.txt");

    }

    @Test
    public void TestPartOne() {
        Assertions.assertEquals(127, EncodingError.findInvalidNumber()[1]);
    }

    @Test
    public void TestPartTwo() {
        Assertions.assertEquals(62, EncodingError.findEncryptionWeakness());
    }
}
