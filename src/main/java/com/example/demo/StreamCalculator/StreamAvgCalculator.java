package com.example.demo.StreamCalculator;

import Calculator.AbstractCalculator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StreamAvgCalculator extends AbstractCalculator {
    public StreamAvgCalculator(List<Integer> nums) {
        super(nums);
        super.setOperationName("Avg");
    }

    @Override
    public int getResult() {
        return (int) nums.stream().mapToInt(i -> i).average()
                .orElseThrow(()->new NullPointerException("Num list is null"));
    }
}
