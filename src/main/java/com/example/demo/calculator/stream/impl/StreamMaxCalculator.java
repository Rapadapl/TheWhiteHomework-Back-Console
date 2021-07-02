package com.example.demo.calculator.stream.impl;

import com.example.demo.calculator.AbstractCalculator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ConditionalOnProperty(name = "calculator.max", havingValue = "true")
public class StreamMaxCalculator extends AbstractCalculator {
    public StreamMaxCalculator(List<Integer> nums) {
        super(nums);
        super.setOperationName("Max");
    }

    @Override
    public int getResult() {
        return nums.stream().max(Integer::compare)
                .orElseThrow(() -> new NullPointerException("Num list is null"));
    }
}
