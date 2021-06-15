package com.example.demo.StreamCalculator;

import Calculator.AbstractCalculator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Service
public class StreamSumCalculator extends AbstractCalculator {
    public StreamSumCalculator(List<Integer> nums) {
        super(nums);
    }

    @Override
    public int getResult() {
        return nums.stream().mapToInt(i -> i).sum();
    }
}
