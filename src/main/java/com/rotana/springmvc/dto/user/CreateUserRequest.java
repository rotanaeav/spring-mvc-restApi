package com.rotana.springmvc.dto.user;

public record CreateUserRequest(
        String username,
        String name,
        String email,
        String gender
) {
}
