package com.example.demo.checker.impl;


import com.example.demo.checker.CheckerException;
import com.example.demo.checker.NumbersChecker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConditionalOnProperty(name = "checkers.length.enabled", havingValue = "true")
public class LengthCheck implements NumbersChecker {

    @Value("${checkers.length}")
    private int checkersLength;

    @Override
    public void checkNumbers(List<Integer> nums) {
        if (nums.size() > checkersLength)
            throw new CheckerException(String.format("Length is higher than %d", checkersLength));
    }
}
