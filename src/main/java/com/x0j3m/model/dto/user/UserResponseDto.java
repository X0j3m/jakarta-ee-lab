package com.x0j3m.model.dto.user;

import com.x0j3m.model.dto.politician.PoliticianResponseDto;
import lombok.Builder;

import java.util.List;

@Builder
public record UserResponseDto(
        String id,
        String username,
        String birthDate,
        String role,
        List<PoliticianResponseDto> politicians
) {}
