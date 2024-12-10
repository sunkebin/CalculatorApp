package com.projects.calculator_backend;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/calculator")
public class CalculateController {

    @GetMapping("/evaluate")
    public ResponseEntity<Object> evaluate(@RequestParam String expression) {

        Map<String, Object> response = new HashMap<>();

        try {
            String regex = "(-?\\d*\\.?\\d+)([+\\-*/])(-?\\d*\\.?\\d+)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(expression);

            if (!matcher.matches()) {
                response.put("error", "Invalid expression format");
                return ResponseEntity.status(400).body(response);
            }

            double num1 = Double.parseDouble(matcher.group(1));
            double num2 = Double.parseDouble(matcher.group(3));
            String operator = matcher.group(2);

            double result = 0;
            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        response.put("error", "Division by zero is not allowed");
                        return ResponseEntity.status(400).body(response);
                    }
                    result = num1 / num2;
                    break;
                default:
                    response.put("error", "Unsupported operator");
                    return ResponseEntity.status(400).body(response);
            }

            response.put("result", result);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "An error occurred while processing the expression");
            return ResponseEntity.status(400).body(response);
        }
    }

}

