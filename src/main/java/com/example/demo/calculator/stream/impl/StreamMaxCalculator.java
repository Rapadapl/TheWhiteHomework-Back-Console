package com.example.demo.calculator.stream.impl;

import com.example.demo.calculator.MathOperation;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ConditionalOnProperty(name = "calculator.max", havingValue = "true")
@NoArgsConstructor
public class StreamMaxCalculator implements MathOperation {


    public int getResult(List<Integer> nums) {
        return nums.stream().max(Integer::compare)
                .orElseThrow(() -> new NullPointerException("Num list is null"));
    }

    @Override
    public String getOperationName() {
        return "Max";
    }

}
