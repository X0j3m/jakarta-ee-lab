package com.x0j3m.model.dto.user;

import lombok.Builder;

@Builder
public record UserResponseDto(
        String id,
        String username,
        String birthDate,
        String role
) {}
