package com.example.demo.StreamCalculator;

import Calculator.AbstractCalculator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StreamSumCalculator extends AbstractCalculator {
    public StreamSumCalculator(List<Integer> nums) {
        super(nums);
        super.setOperationName("Sum");
    }

    @Override
    public int getResult() {
        return nums.stream().mapToInt(i -> i).sum();
    }
}
