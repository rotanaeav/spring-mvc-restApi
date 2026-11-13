package com.rotana.springmvc.dto.user;

public record UserResponse(
        Integer id,
        String username,
        String name,
        String email,
        String gender,
        Boolean active
) {
}
