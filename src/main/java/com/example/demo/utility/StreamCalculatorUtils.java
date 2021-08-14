package com.example.demo.utility;

import com.example.demo.checker.CheckerException;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class StreamCalculatorUtils {

    public static Boolean checkInput(String input) {
        if (input != null)
            return !input.isEmpty() && input.matches("[0-9]+");
        else return false;
    }

    public static List<Integer> strToIntList(String input) {
        if (checkInput(input)) {
            try {
                Integer.parseInt(input);
            } catch (Exception e) {
                throw new CheckerException("Too big number for operating");
            }
            return input.chars().map(Character::getNumericValue).boxed().collect(Collectors.toList());
        } else throw new CheckerException("Argument is null or has illegal type");
    }
}
