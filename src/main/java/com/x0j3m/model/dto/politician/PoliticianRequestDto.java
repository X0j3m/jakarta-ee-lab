package com.x0j3m.model.dto.politician;

import com.x0j3m.model.dto.IdRequestDto;

public record PoliticianRequestDto(
        String name,
        String surname,
        String birthDate,
        String constituency,
        IdRequestDto politicalParty
) {}
