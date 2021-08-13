package com.example.demo.actions;

import com.example.demo.utility.StreamCalculatorUtils;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class NumberValidatorAction {

    public boolean validate(String nums) {
        return StreamCalculatorUtils.checkInput(nums);
    }
}
