package main.java.aoc02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PasswordPhilosophy {
    static List<String> parseInput(String fileLocation) throws IOException {
        FileReader fileReader = new FileReader(fileLocation);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        ArrayList parsedFile = new ArrayList<String>();
        String line = null;
        while((line = bufferedReader.readLine()) != null) {
            parsedFile.add(line);
        }

        return parsedFile;
    }

    static List<List<String>> parseLines(List<String> input) {
        List<List<String>> result = new ArrayList<>(new ArrayList<>());
        input.stream().map(line -> Arrays.asList(line.split("[\\s-]"))).forEach(result::add);
        return result;
    }

    static long countOccurrence(String input, char target) {
        return input.codePoints()
                .mapToObj(c -> (char) c)
                .filter(letter -> letter.equals(target))
                .count();
    }

    public static void main(String[] args) {
        try {
            List<List<String>> data = PasswordPhilosophy.parseLines(PasswordPhilosophy.parseInput("resources/input02.txt"));
            data.stream().forEach(System.out::println);
            long result = data.stream()
                    .filter(password -> countOccurrence(password.get(3), password.get(2).charAt(0)) >= Integer.valueOf(password.get(0)) &&
                            countOccurrence(password.get(3), password.get(2).charAt(0)) <= Integer.valueOf(password.get(1)))
                    .count();
            System.out.println("First part " + result);

            long result2 = data.stream()
                    .filter(password -> password.get(3).charAt(Integer.valueOf(password.get(0))-1)== password.get(2).charAt(0) ^
                            password.get(3).charAt(Integer.valueOf(password.get(1))-1) == password.get(2).charAt(0))
                    .count();
            System.out.println("Second part " + result2);

        } catch(IOException e) {
            System.out.println("FAAAAIIIILLLL");
        }
    }
}
