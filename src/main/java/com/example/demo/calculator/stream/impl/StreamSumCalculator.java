package com.example.demo.calculator.stream.impl;

import com.example.demo.calculator.MathOperation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ConditionalOnProperty(name = "calculator.sum", havingValue = "true")
public class StreamSumCalculator implements MathOperation {

    @Override
    public int getResult(List<Integer> nums) {
        return nums.stream().mapToInt(i -> i).sum();
    }

    @Override
    public String getOperationName() {
        return "Sum";
    }
}
