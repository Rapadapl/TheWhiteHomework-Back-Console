package com.example.demo.calculator;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public abstract class AbstractCalculator implements Operation {
    protected List<Integer> nums;
    private String operationName;

    public AbstractCalculator(List<Integer> nums) {
        this.nums = nums;
    }

}
