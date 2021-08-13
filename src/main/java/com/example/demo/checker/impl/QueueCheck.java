package com.example.demo.checker.impl;

import com.example.demo.checker.CheckerException;
import com.example.demo.checker.NumbersChecker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@ConditionalOnProperty(name = "checkers.queue.enabled", havingValue = "true")

public class QueueCheck implements NumbersChecker {

    @Value("${checkers.queue}")
    private String checkersQueue;

    @Override
    public void checkNumbers(List<Integer> nums) {

        String numberString = nums.stream().map(String::valueOf)
                                  .collect(Collectors.joining(""));

        if (numberString.contains(checkersQueue))
            throw new CheckerException(String.format("Input numbers contains %s", checkersQueue));
    }
}

