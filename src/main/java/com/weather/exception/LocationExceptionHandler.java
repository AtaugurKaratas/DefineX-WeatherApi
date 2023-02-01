package com.weather.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class LocationExceptionHandler {
    @ExceptionHandler(LocationNotFoundException.class)
    public ResponseEntity<?> locationNotFound(LocationNotFoundException locationNotFoundException){
        List<String> detail = new ArrayList<>();
        detail.add(locationNotFoundException.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("Location Not Found", detail);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
