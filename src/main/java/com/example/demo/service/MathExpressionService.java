package com.example.demo.service;


import com.example.demo.entity.MathExpressions;
import com.example.demo.service.arg.MathExpressionFindArgs;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;


public interface MathExpressionService {
    MathExpressions create(String number, String result);

    List<MathExpressions> getFiltered(MathExpressionFindArgs args, Pageable pageable);

    List<String> getAll(Pageable pageable);

    List<MathExpressions> getBetweenDates(LocalDateTime fromDateTime, LocalDateTime toDateTime);
}
