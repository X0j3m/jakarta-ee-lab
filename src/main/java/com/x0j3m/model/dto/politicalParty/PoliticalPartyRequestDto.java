package com.x0j3m.model.dto.politicalParty;

import lombok.Builder;

@Builder
public record PoliticalPartyRequestDto(
        String name,
        String foundationDate,
        String orientation
) {}
