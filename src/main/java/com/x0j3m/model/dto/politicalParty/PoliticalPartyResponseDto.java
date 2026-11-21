package com.x0j3m.model.dto.politicalParty;

import com.x0j3m.model.dto.politician.PoliticianResponseDto;
import lombok.Builder;

import java.util.List;

@Builder
public record PoliticalPartyResponseDto(
        String id,
        String name,
        String foundationDate,
        String orientation,
        List<PoliticianResponseDto> politicians
) {}
