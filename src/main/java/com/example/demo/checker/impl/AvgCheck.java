package com.example.demo.checker.impl;

import com.example.demo.calculator.stream.impl.StreamAvgCalculator;
import com.example.demo.checker.CheckerException;
import com.example.demo.checker.NumbersChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConditionalOnProperty(name = "checkers.avg.enabled", havingValue = "true")
@RequiredArgsConstructor
public class AvgCheck implements NumbersChecker {

    private final StreamAvgCalculator avgCalculator;
    @Value("${checkers.avg}")
    private int checkersAvg;

    @Override
    public void checkNumbers(List<Integer> nums) {

        if (avgCalculator.getResult(nums) == checkersAvg)
            throw new CheckerException(String.format("%s is equal to %d", avgCalculator.getOperationName(), checkersAvg));
    }
}
