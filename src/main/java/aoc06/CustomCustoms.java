package main.java.aoc06;

import com.sun.javafx.logging.JFRInputEvent;
import main.java.shared.InputParser;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CustomCustoms {
    public static int evaluateForms(List<String> input) {
        List<Set> result = new ArrayList<>(new HashSet<>());
        Set<Character> form = new HashSet();
        for(int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            Set<Character> finalForm = form;
            if(!line.equals("")) {

                line.codePoints()
                        .forEach(str -> finalForm.add((char) str));
                if(i == input.size() - 1) {
                    result.add(finalForm);
                }
            } else {
                result.add(finalForm);
                form = new HashSet();
            }
        }
        return result.stream().mapToInt(Set::size).sum();
    }

    public static int evaluateConjForms(List<String> input) {
        List<Set> result = new ArrayList<>(new HashSet<>());
        Set<Character> form = new HashSet();
        for(int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            if(!line.equals("")) {
                Set intermediateResult = new HashSet();
                line.codePoints()
                        .forEach(str -> intermediateResult.add((char) str));
                if(i == 0 || input.get(i - 1).equals("")) {
                    form = intermediateResult;
                } else {
                    form.retainAll(intermediateResult);
                }
                if(i == input.size() - 1) {
                    form.retainAll(intermediateResult);
                    result.add(form);
                }
            } else {
                result.add(form);
                form = new HashSet();
            }
        }
        return result.stream().mapToInt(Set::size).sum();
    }

    public static void main(String[] args) {
        try {
            List<String> data = InputParser.parseReport("resources/input06.txt");
            System.out.println("Part One " + CustomCustoms.evaluateForms(data));
            System.out.println("Part Two " + CustomCustoms.evaluateConjForms(data));
        } catch(IOException e) {
            System.out.println("parsing failed, you bum.");
        }
    }
}
