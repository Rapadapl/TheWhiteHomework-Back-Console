package com.example.demo.checker.impl;

import com.example.demo.calculator.stream.impl.StreamSumCalculator;
import com.example.demo.checker.CheckerException;
import com.example.demo.checker.NumbersChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConditionalOnProperty(name = "checkers.sum.enabled", havingValue = "true")
@RequiredArgsConstructor
public class SumCheck implements NumbersChecker {

    private final StreamSumCalculator sumCalculator;
    @Value("${checkers.sum}")
    private int checkersSum;

    @Override
    public void checkNumbers(List<Integer> nums) {

        if (sumCalculator.getResult(nums) == checkersSum)
            throw new CheckerException(String.format("%s is equal to %d", sumCalculator.getOperationName(), checkersSum));
    }
}
