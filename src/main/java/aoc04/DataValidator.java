package main.java.aoc04;

import java.util.Arrays;
import java.util.List;

public class DataValidator {

    static boolean validateBYR(String byr) {
        return byr.length() == 4 && Integer.parseInt(byr) >= 1920 && Integer.parseInt(byr) <= 2002;
    }

    static boolean validateIYR(String iyr) {
        return iyr.length() == 4 && Integer.parseInt(iyr) >= 2010 && Integer.parseInt(iyr) <= 2020;
    }

    static boolean validateEYR(String eyr) {
        return eyr.length() == 4 && Integer.parseInt(eyr) >= 2020 && Integer.parseInt(eyr) <= 2030;
    }

    static boolean validateHGT(String hgt) {
        String unit = hgt.substring(hgt.length() - 2, hgt.length());
        switch (unit) {
            case "cm":
                int heightCM = Integer.valueOf(hgt.substring(0, hgt.length() - 2));
                if (heightCM >= 150 && heightCM <= 193) {
                    return true;
                } else {
                    return false;
                }
            case "in":
                int heightIN = Integer.valueOf(hgt.substring(0, hgt.length() - 2));
                if (heightIN >= 59 && heightIN <= 76) {
                    return true;
                } else {
                    return false;
                }
            default:
                return false;
        }
    }

    static boolean validateHCL(String hcl) {
        char start = hcl.charAt(0);

        long alphaNumCount = hcl.substring(1, hcl.length()).codePoints()
                .map(part -> (char) part)
                .filter(ch -> ch >= 'a' && ch <= 'f' || ch >= '0' && ch <= '9')
                .count();

        return start == '#' && alphaNumCount == 6;

    }

    static boolean validateECL(String ecl) {
        List<String> VALID_ECL = Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
        return VALID_ECL.contains(ecl);
    }

    static boolean validatePID(String pid) {
        return pid.codePoints()
                .map(st -> (char) st)
                .filter(Character::isDigit)
                .count() == 9;
    }
}
