package com.example.demo.runner;

import com.example.demo.Utility.Utils;
import com.example.demo.controller.CalculatorController;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class ConsoleRunner implements CommandLineRunner {

    private final CalculatorController calculatorController;

    private static String readLine() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    private void output(Map<String, Integer> result) {

        result.forEach((key, value) -> System.out.printf("%s:  %d\n", key, value));

    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Введите число:");
        String input = readLine();
        while (!Utils.checkInput(input)) {
            System.out.println("Некорректный ввод, повторите снова:");
            input = readLine();
        }
        List<Integer> nums = Utils.strToIntList(input);

        Map<String, Integer> result = calculatorController.getCalculationResult(nums);
        output(result);
    }
}
