package com.example.demo.calculator.stream.impl;

import com.example.demo.calculator.MathOperation;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ConditionalOnProperty(name = "calculator.avg", havingValue = "true")
@NoArgsConstructor
public class StreamAvgCalculator implements MathOperation {

    @Override
    public int getResult(List<Integer> nums) {
        return (int) nums.stream().mapToInt(i -> i).average()
                .orElseThrow(() -> new NullPointerException("Num list is null"));
    }

    @Override
    public String getOperationName() {
        return "Avg";
    }
}
