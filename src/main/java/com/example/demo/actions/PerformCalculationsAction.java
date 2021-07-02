package com.example.demo.actions;

import com.example.demo.checker.NumbersChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PerformCalculationsAction {

    private final List<NumbersChecker> checkers;

    public void execute(List<Integer> nums) {
        checkers.forEach(checker -> checker.checkNumbers(nums));
    }
}
