package com.rotana.springmvc.dto;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateEmployeeRequest(
        @NotBlank(message = "Name can't blank")
        @Size(min = 2, max = 50, message = "Name must be between 2 and 50 chars")
        String name,

        @Email(message = "Invalid email format")
        String email,

        @NotNull(message = "Salary can't Nullable")
        @Positive(message = "Salary must positive")
        BigDecimal salary,

//        @NotBlank(message = "Hire date is required")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate hireDate
) {
}
