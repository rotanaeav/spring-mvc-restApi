package com.rotana.springmvc.dto.user;

public record UpdateUserRequest(
        String username,
        String name,
        String email,
        String gender,
        Boolean active
) {
}
