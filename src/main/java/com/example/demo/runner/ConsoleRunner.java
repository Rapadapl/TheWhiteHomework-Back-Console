package com.example.demo.runner;

import com.example.demo.checker.CheckerException;
import com.example.demo.controller.CalculatorController;
import com.example.demo.utility.StreamCalculatorUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class ConsoleRunner implements CommandLineRunner {

    private final static Logger LOGGER = Logger.getLogger(ConsoleRunner.class.getName());

    private final CalculatorController calculatorController;

    private static String readLine() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            return reader.readLine();
        } catch (IOException e) {
            LOGGER.log(Level.INFO, e.toString());
        }
        return "";
    }


    private void output(Map<String, Integer> result) {

        result.forEach((key, value) -> System.out.printf("%s:  %d\n", key, value));

    }

    @Override
    public void run(String... args) {

//        System.out.println("Введите число:");
//        String input = readLine();
//        while (!StreamCalculatorUtils.checkInput(input)) {
//            System.out.println("Некорректный ввод, повторите снова:");
//            input = readLine();
//        }
//        List<Integer> nums = StreamCalculatorUtils.strToIntList(input);
//
//        try {
//            Map<String, Integer> result = calculatorController.getCalculationResult(nums);
//            output(result);
//        } catch (CheckerException e) {
//            System.out.print("WARNING " + e.getMessage());
//        }

    }
}
