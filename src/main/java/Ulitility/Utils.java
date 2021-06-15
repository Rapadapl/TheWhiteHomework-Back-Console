package Ulitility;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Utils {

    public static Boolean checkInput(String input) {
        return !input.isEmpty() && input.matches("[0-9]+");
    }

    public static List<Integer> strToList(String input) {
        if (checkInput(input))
        return input.chars().map(Character::getNumericValue).boxed().collect(Collectors.toList());
        else throw new IllegalArgumentException("Argument is null or has illegal type");
    }
}
