package com.rotana.springmvc.dto.employee;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record TransferEmployeeRequest(
        @NotNull(message = "Please provide valid department ID!")
        UUID newDepartmentId
) {
}
