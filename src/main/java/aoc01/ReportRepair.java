package main.java.aoc01;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReportRepair {
    static List<String> parseReport(String fileLocation) throws IOException {
        FileReader fileReader = new FileReader(fileLocation);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        ArrayList parsedFile = new ArrayList<String>();
        String line = null;
        while((line = bufferedReader.readLine()) != null) {
            parsedFile.add(line);
        }

        return parsedFile;
    }

    public static void main(String[] args) {
        try {
            List<String> data = ReportRepair.parseReport("resources/input.txt");
            for(int i = 0; i < data.size() - 1; i++) {
                for(int j = i + 1; j < data.size(); j++) {
                    if(Integer.parseInt(data.get(i)) + Integer.parseInt(data.get(j)) == 2020) {
                        System.out.println("First part: " + Integer.parseInt(data.get(i)) * Integer.parseInt(data.get(j)));
                    }
                }
            }

            for(int i = 0; i < data.size() - 2; i++) {
                for(int j = i + 1; j < data.size() - 1; j++) {
                    for(int k = j + 1; k < data.size(); k++) {
                        if(Integer.parseInt(data.get(i)) + Integer.parseInt(data.get(j)) + Integer.parseInt(data.get(k)) == 2020) {
                            System.out.println("Second part: " + Integer.parseInt(data.get(i)) * Integer.parseInt(data.get(j)) * Integer.parseInt(data.get(k)));
                        }
                    }
                }
            }


        } catch(IOException e) {
            System.out.println("FAAAAIIIILLLL");
        }


    }
}
