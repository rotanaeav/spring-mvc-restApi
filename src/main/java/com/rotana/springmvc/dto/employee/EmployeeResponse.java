package com.rotana.springmvc.dto.employee;

import com.rotana.springmvc.dto.department.DepartmentResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record EmployeeResponse(
        UUID id,
        String name,
        String email,
        BigDecimal salary,
        LocalDate hireDate,
        Boolean isActive,
        DepartmentResponse department
        ) {
}
