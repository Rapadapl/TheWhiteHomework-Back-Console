package com.example.demo.runner;

import com.example.demo.controller.CalculatorController;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ConsoleRunner implements CommandLineRunner {

    private final CalculatorController calculatorController;

    @Override
    public void run(String... args) throws Exception {
        calculatorController.initialize();
    }
}
