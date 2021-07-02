package com.example.demo.controller;

import com.example.demo.checker.CheckerException;
import com.example.demo.controller.dto.ErrorDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {CheckerException.class})
    public ResponseEntity<Object> numbersBadRequest(RuntimeException exception) {
        return new ResponseEntity<Object>(
                new ErrorDto(exception.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
