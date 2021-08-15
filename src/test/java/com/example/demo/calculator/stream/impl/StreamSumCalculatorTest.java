package com.example.demo.calculator.stream.impl;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StreamSumCalculatorTest {
    private final StreamSumCalculator sum = new StreamSumCalculator();

    @Test
    void calculatorSum() {
        //Act
        Integer actual = sum.getResult(new ArrayList<Integer>(Arrays.asList(1, 2, 3)));


        //Assert
        assertEquals(6, actual);
    }

}