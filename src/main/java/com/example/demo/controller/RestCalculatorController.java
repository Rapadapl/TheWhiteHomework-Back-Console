package com.example.demo.controller;

import com.example.demo.actions.NumberValidatorAction;
import com.example.demo.actions.PerformCalculationsAction;
import com.example.demo.actions.StringToListAction;
import com.example.demo.calculator.MathOperation;
import com.example.demo.notifications.annotation.Notify;
import com.example.demo.service.MathExpressionService;
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
    private final MathExpressionService expressions;

    @Notify
    @PostMapping("all")
    public Map<String, Integer> getCalculationResult(
            @RequestParam(name = "number") String strNums) {

        numberValidatorAction.validate(strNums);

        List<Integer> nums = stringToListAction.strToList(strNums);
        Map<String, Integer> answer = new HashMap<>();

        calculators.forEach(i -> {
            answer.put(i.getOperationName(), i.getResult(nums));
        });

        performCalculationsAction.execute(nums);

        expressions.create(Integer.parseInt(strNums), answer.toString());
        return answer;
    }

}
