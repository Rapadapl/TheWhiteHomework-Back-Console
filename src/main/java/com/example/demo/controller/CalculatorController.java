package com.example.demo.controller;

import Calculator.AbstractCalculator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class CalculatorController {

    private final List<AbstractCalculator> calculators;


    public Map<String, Integer> getCalculationResult(List<Integer> nums) {

        Map<String, Integer> answer = new HashMap<>();

        calculators.forEach(i -> {
            i.setNums(nums);
            answer.put(i.getOperationName(), i.getResult());
        });
        return answer;
    }


}
