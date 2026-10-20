package com.x0j3m.model.dto.politician;

import lombok.Builder;

@Builder
public record PoliticianResponseDto(
        String id,
        String name,
        String surname,
        String birthDate,
        String constituency,
        String politicalParty
) {}
