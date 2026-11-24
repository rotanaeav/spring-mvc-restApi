package com.rotana.springmvc.dto.department;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;
@Builder
public record DepartmentStatisticsResponse(
        String departmentName,
        Long totalEmployees,
        Long totalActiveEmployees,
        BigDecimal averageSalary
) {
}
