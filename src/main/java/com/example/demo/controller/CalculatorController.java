package com.example.demo.controller;

import Calculator.AbstractCalculator;
import Utility.Utils;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class CalculatorController {

    private final List<AbstractCalculator> calculators;


    public Map<String, Integer> getCalculationResult (List<Integer> nums) {

        Map<String, Integer> answer = new HashMap<>();

        calculators.forEach(i -> {
            i.setNums(nums);
            answer.put(i.getOperationName(), i.getResult());
        });
        return answer;
    }


}
