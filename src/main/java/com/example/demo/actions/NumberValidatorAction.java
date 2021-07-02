package com.example.demo.actions;

import com.example.demo.Utility.StreamCalculatorUtils;
import com.example.demo.controller.dto.NewCalculationDto;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class NumberValidatorAction {

    public boolean validate(String nums) {
        return StreamCalculatorUtils.checkInput(nums);
    }
}
