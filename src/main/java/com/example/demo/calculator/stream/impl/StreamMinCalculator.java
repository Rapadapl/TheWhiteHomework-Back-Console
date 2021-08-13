package com.example.demo.calculator.stream.impl;

import com.example.demo.calculator.MathOperation;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ConditionalOnProperty(name = "calculator.min", havingValue = "true")
@NoArgsConstructor
public class StreamMinCalculator implements MathOperation {

    @Override
    public int getResult(List<Integer> nums) {
        return nums.stream().min(Integer::compare)
                   .orElseThrow(() -> new NullPointerException("Num list is null"));
    }

    @Override
    public String getOperationName() {
        return "Min";
    }
}
