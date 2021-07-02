package com.example.demo.actions;

import com.example.demo.Utility.StreamCalculatorUtils;
import com.example.demo.controller.dto.NewCalculationDto;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class StringToListAction {

    public List<Integer> strToList(String nums){
        return StreamCalculatorUtils.strToIntList(nums);
    }
}
