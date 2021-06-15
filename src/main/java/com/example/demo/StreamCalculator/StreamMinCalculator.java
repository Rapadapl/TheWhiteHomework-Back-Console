package com.example.demo.StreamCalculator;

import Calculator.AbstractCalculator;

import java.util.List;

public class StreamMinCalculator extends AbstractCalculator {
    public StreamMinCalculator(List<Integer> nums) {
        super(nums);
    }

    @Override
    public int getResult() {
        return nums.stream().min(Integer::compare)
                .orElseThrow(()->new NullPointerException("Num list is null"));
    }
}
