package com.example.demo.calculator.stream.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StreamAvgCalculatorTest {

    private final StreamAvgCalculator avg = new StreamAvgCalculator();

    @Test
    void calculatorAvg() {
        //Act
        Integer actual = avg.getResult(new ArrayList<Integer>(Arrays.asList(1, 2, 3)));


        //Assert
        assertEquals(2, actual);
    }

    @Test
    void calculatorAvgEmptyInitial() {
        Exception exception = Assertions.assertThrows(NullPointerException.class,
                                                      () -> avg.getResult(new ArrayList<Integer>()));
        String expectedMessage = "Num list is null";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));

    }
}