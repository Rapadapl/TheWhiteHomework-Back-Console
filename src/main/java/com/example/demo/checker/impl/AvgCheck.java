package com.example.demo.checker.impl;

import com.example.demo.Utility.PropertiesExtractor;
import com.example.demo.calculator.stream.impl.StreamAvgCalculator;
import com.example.demo.calculator.stream.impl.StreamSumCalculator;
import com.example.demo.checker.CheckerException;
import com.example.demo.checker.NumbersChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConditionalOnProperty(name = "checkers.avg.enabled", havingValue = "true")
@RequiredArgsConstructor
public class AvgCheck implements NumbersChecker {

    private static final int AVG = Integer.parseInt(PropertiesExtractor.getProperty("checkers.avg"));
    private final StreamAvgCalculator avgCalculator;

    @Override
    public void checkNumbers(List<Integer> nums) {

        if (avgCalculator.getResult(nums) == AVG)
            throw new CheckerException(String.format("%s is equal to %d", avgCalculator.getOperationName(), AVG));
    }
}
