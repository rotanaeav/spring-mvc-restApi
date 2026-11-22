package com.rotana.springmvc.dto.employee;

import com.rotana.springmvc.domain.Department;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

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
        LocalDate hireDate,

//        @NotBlank (message = "Department can't blank")
        @NotNull(message = "Department can't Nullable")
        UUID departmentId
) {
}
