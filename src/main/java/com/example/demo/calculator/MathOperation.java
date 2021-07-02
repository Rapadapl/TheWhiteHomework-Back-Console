package com.example.demo.calculator;

import java.util.List;

public interface MathOperation {
    int getResult(List<Integer> nums);

    String getOperationName();
}
