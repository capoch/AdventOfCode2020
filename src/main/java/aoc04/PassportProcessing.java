package main.java.aoc04;

import main.java.shared.InputParser;
import java.io.IOException;
import java.util.*;

public class PassportProcessing {
    static final List<String> MANDATORY_ENTRIES = Arrays.asList("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");

    static List<Map<String,String>> makePassports(List<String> input) {
        List<Map<String,String>> passports = new ArrayList<>();
        Map passport = new HashMap();
        for(int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            Map finalPassport = passport;
            if (!line.equals("")) {
                Arrays.asList(input.get(i).split(" ")).stream()
                        .map(entry -> entry.split(":"))
                        .forEach(array -> finalPassport.put(array[0],array[1]));
                if(i == input.size() - 1) {
                    passports.add(finalPassport);
                }
            } else {
                passports.add(finalPassport);
                passport = new HashMap();
            }
        }
        return passports;
    }

    static boolean validatePassport(Map<String, String> passport) {
        return MANDATORY_ENTRIES.stream().filter(key -> passport.keySet().contains(key)).count() == MANDATORY_ENTRIES.size();
    }

    static boolean validatePassportData(Map<String, String> passport) {
        return DataValidator.validateBYR(passport.get("byr")) &&
                DataValidator.validateIYR(passport.get("iyr")) &&
                DataValidator.validateEYR(passport.get("eyr")) &&
                DataValidator.validateHGT(passport.get("hgt")) &&
                DataValidator.validateHCL(passport.get("hcl")) &&
                DataValidator.validateECL(passport.get("ecl")) &&
                DataValidator.validatePID(passport.get("pid"));
    }




    public static void main(String[] args) {
        try {
            List<String> data = InputParser.parseReport("resources/input04.txt");
            List<Map<String, String>> passports = PassportProcessing.makePassports(data);
            System.out.println("First part " + passports.stream().filter(PassportProcessing::validatePassport).count());
            System.out.println("Second Part " + passports.stream()
                    .filter(PassportProcessing::validatePassport)
                    .filter(PassportProcessing::validatePassportData)
                    .count());
        } catch(IOException e) {
            System.out.println("Error: parsing failed");
        }
    }
}
