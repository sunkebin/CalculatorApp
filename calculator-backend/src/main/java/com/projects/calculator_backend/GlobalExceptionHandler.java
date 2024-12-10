package com.projects.calculator_backend;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.script.ScriptException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
    }

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<String> handleArithmeticException(ArithmeticException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Division by zero is not allowed.");
    }

    @ExceptionHandler(ScriptException.class)
    public ResponseEntity<String> handleScriptException(ScriptException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Invalid Expression");
    }
}
