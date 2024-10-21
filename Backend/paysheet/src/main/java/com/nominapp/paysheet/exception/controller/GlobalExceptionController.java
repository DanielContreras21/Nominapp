package com.nominapp.paysheet.exception.controller;

import com.nominapp.paysheet.exception.NotFoundException;
import com.nominapp.paysheet.exception.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse> handlerNotFoundException(NotFoundException notFoundException){
        String message = notFoundException.getMessage();
        ApiResponse response = ApiResponse.builder()
                .message(message)
                .success(true)
                .status(HttpStatus.NOT_FOUND)
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
