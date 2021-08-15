package com.example.demo.controller;

import com.example.demo.entity.MathExpressions;
import com.example.demo.service.MathExpressionService;
import com.example.demo.service.arg.MathExpressionFindArgs;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Slf4j
@RestController
@RequestMapping(value = "calculator/log")
@RequiredArgsConstructor
public class LoggerCalculationController {

    private final MathExpressionService mathExpressionService;

    @GetMapping("successful/list")
    @Operation(summary = "Returns all successful calculations")
    public List<String> getAllLogs(
            @PageableDefault(sort = "creationDate", direction = DESC) Pageable pageable) {

        log.info("Returning all successful calculations");
        return mathExpressionService.getAll(pageable);
    }

    @GetMapping("successful/list/filter")
    @Operation(summary = "Returns all successful calculations that match search criteria")
    public List<MathExpressions> getFilteredLogs(
            MathExpressionFindArgs arg,
            @PageableDefault(sort = "creationDate", direction = DESC) Pageable pageable) {

        log.info("Returning all successful calculations that match search criteria");
        return mathExpressionService.getFiltered(arg, pageable);
    }

}
