package com.x0j3m.model.dto;

import lombok.Builder;

@Builder
public record UserResponseDto(
        String id,
        String username,
        String age,
        String role
) {}
