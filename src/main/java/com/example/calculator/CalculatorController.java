package com.example.calculator;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/calc")
public class CalculatorController {
    private final CalculatorService service;

    public CalculatorController(CalculatorService service) {
        this.service = service;
    }

    public record Result(BigDecimal result) {}

    @GetMapping("/add")
    public Result add(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        return new Result(service.add(a, b));
    }

    @GetMapping("/subtract")
    public Result subtract(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        return new Result(service.subtract(a, b));
    }

    @GetMapping("/multiply")
    public Result multiply(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        return new Result(service.multiply(a, b));
    }

    @GetMapping("/divide")
    public Result divide(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        try {
            return new Result(service.divide(a, b));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
