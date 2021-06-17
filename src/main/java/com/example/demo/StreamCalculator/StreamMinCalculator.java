package com.example.demo.StreamCalculator;

import Calculator.AbstractCalculator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ConditionalOnProperty(name = "calculator.min", havingValue  = "true")
public class StreamMinCalculator extends AbstractCalculator {
    public StreamMinCalculator(List<Integer> nums) {
        super(nums);
        super.setOperationName("Min");
    }

    @Override
    public int getResult() {
        return nums.stream().min(Integer::compare)
                .orElseThrow(()->new NullPointerException("Num list is null"));
    }
}
