package com.example.demo.controller;

import com.example.demo.actions.NumberValidatorAction;
import com.example.demo.actions.PerformCalculationsAction;
import com.example.demo.actions.StringToListAction;
import com.example.demo.calculator.MathOperation;
import com.example.demo.checker.CheckerException;
import com.example.demo.controller.dto.NewCalculationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class RestCalculatorController {
    private final List<MathOperation> calculators;
    private final PerformCalculationsAction performCalculationsAction;
    private final NumberValidatorAction numberValidatorAction;
    private final StringToListAction stringToListAction;

    @PostMapping("calculator/all")
    public Map<String, Integer> getCalculationResult(
            @RequestParam(name = "number", required = true) String strNums) {

        if (!numberValidatorAction.validate(strNums))
            throw new CheckerException("Input numbers have wrong format");

        List<Integer> nums = stringToListAction.strToList(strNums);
        Map<String, Integer> answer = new HashMap<>();

        calculators.forEach(i -> {
            answer.put(i.getOperationName(), i.getResult(nums));
        });

        performCalculationsAction.execute(nums);

        return answer;
    }


}
