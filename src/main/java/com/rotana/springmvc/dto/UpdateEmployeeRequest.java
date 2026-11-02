package com.rotana.springmvc.dto;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdateEmployeeRequest(
        @Size(min = 2, max = 50, message = "Name must be between 2 and 50 chars")
        String name,

        @Email(message = "Invalid email format")
        String email,

        @Positive(message = "Salary must positive")
        BigDecimal salary,

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate hireDate,

        Boolean isActive
) {
}
