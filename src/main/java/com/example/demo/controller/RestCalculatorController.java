package com.example.demo.controller;

import com.example.demo.actions.NumberValidatorAction;
import com.example.demo.actions.PerformCalculationsAction;
import com.example.demo.actions.StringToListAction;
import com.example.demo.calculator.MathOperation;
import com.example.demo.checker.CheckerException;
import com.example.demo.notifications.annotation.Notificator;
import com.example.demo.service.MathExpressionServiceImpl;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = "Calculator controller")
@RequestMapping(value = "calculator")
public class RestCalculatorController {
    private final List<MathOperation> calculators;
    private final PerformCalculationsAction performCalculationsAction;
    private final NumberValidatorAction numberValidatorAction;
    private final StringToListAction stringToListAction;
    private final MathExpressionServiceImpl expressions;


    @Notificator
    @PostMapping("all")
    public Map<String, Integer> getCalculationResult(
            @RequestParam(name = "number", required = true) String strNums) {

        if (!numberValidatorAction.validate(strNums))
            throw new CheckerException("Input numbers have wrong format");


        List<Integer> nums = stringToListAction.strToList(strNums);
        Map<String, Integer> answer = new HashMap<>();

        try {
            Integer.parseInt(strNums);
        } catch (Exception e) {
            log.info("Big number");
            throw new CheckerException("Too big number for operating");
        }


        calculators.forEach(i -> {
            answer.put(i.getOperationName(), i.getResult(nums));
        });

        performCalculationsAction.execute(nums);

        expressions.create(Integer.parseInt(strNums), answer.toString());
        return answer;
    }

}
