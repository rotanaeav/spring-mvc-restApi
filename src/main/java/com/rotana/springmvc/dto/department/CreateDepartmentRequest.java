package com.rotana.springmvc.dto.department;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateDepartmentRequest(
        @NotNull
        @NotBlank
        @Size(min = 3, max = 50)
        String name,
        Boolean isActive
) {
}
