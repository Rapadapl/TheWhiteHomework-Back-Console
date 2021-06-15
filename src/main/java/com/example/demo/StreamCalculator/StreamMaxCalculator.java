package com.example.demo.StreamCalculator;

import Calculator.AbstractCalculator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StreamMaxCalculator extends AbstractCalculator {
    public StreamMaxCalculator(List<Integer> nums) {
        super(nums);
    }

    @Override
    public int getResult() {
        return nums.stream().max(Integer::compare)
                .orElseThrow(()->new NullPointerException("Num list is null"));
    }
}
