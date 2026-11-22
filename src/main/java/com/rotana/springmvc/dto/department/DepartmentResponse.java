package com.rotana.springmvc.dto.department;

import java.util.UUID;

public record DepartmentResponse(
        UUID id,
        String name,
        Boolean isActive
) {
}
