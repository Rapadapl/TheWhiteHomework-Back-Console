package com.example.demo.calculator.stream.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StreamMinCalculatorTest {
    private final StreamMinCalculator min = new StreamMinCalculator();

    @Test
    void calculatorMin() {
        //Act
        Integer actual = min.getResult(new ArrayList<Integer>(Arrays.asList(1, 2, 3)));


        //Assert
        assertEquals(1, actual);
    }

    @Test
    void calculatorMinEmptyInitial() {
        Exception exception = Assertions.assertThrows(NullPointerException.class,
                                                      () -> min.getResult(new ArrayList<Integer>()));
        String expectedMessage = "Num list is null";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));

    }
}