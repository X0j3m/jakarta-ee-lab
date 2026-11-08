package com.x0j3m.model.dto.politician;

public record PoliticianRequestDto(
        String name,
        String surname,
        String birthDate,
        String constituency,
        String userId
) {}
