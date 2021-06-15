package com.example.demo.controller;

import Calculator.AbstractCalculator;
import Utility.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Controller
public class ConsoleCalculatorController {

    private List<AbstractCalculator> calculators;

    @Autowired
    public ConsoleCalculatorController(AbstractCalculator calculator) {
        this.calculators.add(calculator);
    }


//    private AbstractCalculator calculator;
//    @Autowired
//    public ConsoleCalculatorController(AbstractCalculator calculator) {
//        this.calculator = calculator;
//    }

    private static String readLine() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

//    private void getResult(List<Integer> nums){
//        calculator.setNums(nums);
//        int result = calculator.getResult();
//        output(result,calculator);
//    }

    public void initialize() {
        System.out.println("Введите число:");
        String input = readLine();
        while (!Utils.checkInput(input)) {
            System.out.println("Некорректный ввод, повторите снова:");
            input = readLine();
        }
        List<Integer> nums = Utils.strToIntList(input);
        getResult(nums);
    }

    private void getResult(List<Integer> nums) {
        calculators.forEach(i -> {
            i.setNums(nums);
            output(i.getResult(),i);
        });

    }

    private void output(int result, AbstractCalculator calculation) {
        System.out.printf("%s:  %d\n",
                calculation.getOperationName(),
                result);
    }

}
