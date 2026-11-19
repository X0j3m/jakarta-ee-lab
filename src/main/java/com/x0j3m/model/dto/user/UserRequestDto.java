package com.x0j3m.model.dto.user;

public record UserRequestDto(
        String username,
        String password,
        String birthDate,
        String role
) {}
