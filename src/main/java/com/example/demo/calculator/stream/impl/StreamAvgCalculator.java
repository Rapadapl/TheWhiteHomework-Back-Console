package com.example.demo.calculator.stream.impl;

import com.example.demo.calculator.AbstractCalculator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ConditionalOnProperty(name = "calculator.avg", havingValue = "true")
public class StreamAvgCalculator extends AbstractCalculator {
    public StreamAvgCalculator(List<Integer> nums) {
        super(nums);
        super.setOperationName("Avg");
    }

    @Override
    public int getResult() {
        return (int) nums.stream().mapToInt(i -> i).average()
                .orElseThrow(() -> new NullPointerException("Num list is null"));
    }
}
