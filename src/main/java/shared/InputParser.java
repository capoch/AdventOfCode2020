package main.java.shared;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputParser {
    public static List<String> parseReport(String fileLocation) throws IOException {
        FileReader fileReader = new FileReader(fileLocation);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        ArrayList parsedFile = new ArrayList<String>();
        String line = null;
        while((line = bufferedReader.readLine()) != null) {
            parsedFile.add(line);
        }

        return parsedFile;
    }
}
