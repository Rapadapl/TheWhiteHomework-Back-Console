package com.example.demo.calculator.stream.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StreamMaxCalculatorTest {
    private final StreamMaxCalculator max = new StreamMaxCalculator();

    @Test
    void calculatorMax() {
        //Act
        Integer actual = max.getResult(new ArrayList<Integer>(Arrays.asList(1, 2, 3)));


        //Assert
        assertEquals(3, actual);
    }

    @Test
    void calculatorMaxEmptyInitial() {
        Exception exception = Assertions.assertThrows(NullPointerException.class,
                                                      () -> max.getResult(new ArrayList<Integer>()));
        String expectedMessage = "Num list is null";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));

    }
}