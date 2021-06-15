package Utility;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class UtilsTest {

    @Test
    public void validInput() {
        String input = "123321";
        boolean result = Utils.checkInput(input);
        Assertions.assertTrue(result);
    }

    @Test
    public void validParse() {
        String input = "124";
        List<Integer> result = Utils.strToIntList(input);
        List<Integer> expected = Arrays.asList(1, 2, 4);
        Assertions.assertEquals(result, expected);
    }

    @Test
    public void invalidInput() {
        String input = "123as321";
        boolean result = Utils.checkInput(input);
        Assertions.assertFalse(result);
    }

    @Test
    public void emptyInput() {
        String input = "";
        boolean result = Utils.checkInput(input);
        Assertions.assertFalse(result);
    }

    @Test
    public void nullInput() {
        String input = null;
        boolean result = Utils.checkInput(input);
        Assertions.assertFalse(result);
    }

    @Test
    public void invalidParse() {
        String input = "121dfg4";
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> Utils.strToIntList(input));
        String expectedMessage = "Argument is null or has illegal type";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void emptyParse() {
        String input = "";
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> Utils.strToIntList(input));
        String expectedMessage = "Argument is null or has illegal type";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    public void nullParse() {
        String input = null;
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> Utils.strToIntList(input));
        String expectedMessage = "Argument is null or has illegal type";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

}