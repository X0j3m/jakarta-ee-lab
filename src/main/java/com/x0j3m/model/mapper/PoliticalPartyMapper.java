package com.x0j3m.model.mapper;

import com.x0j3m.model.dto.politicalParty.PoliticalPartyRequestDto;
import com.x0j3m.model.dto.politicalParty.PoliticalPartyResponseDto;
import com.x0j3m.model.entity.Orientation;
import com.x0j3m.model.entity.PoliticalPartyEntity;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@ApplicationScoped
@NoArgsConstructor
public class PoliticalPartyMapper {
    public PoliticalPartyEntity toEntity(UUID id, PoliticalPartyRequestDto politicalPartyDto) {
        return PoliticalPartyEntity.builder()
                .id(id)
                .name(politicalPartyDto.name())
                .foundationDate(LocalDate.parse(politicalPartyDto.foundationDate()))
                .orientation(Orientation.valueOf(politicalPartyDto.orientation()))
                .build();
    }

    public PoliticalPartyResponseDto toDto(PoliticalPartyEntity politicalPartyEntity) {
        return PoliticalPartyResponseDto.builder()
                .id(politicalPartyEntity.getId().toString())
                .name(politicalPartyEntity.getName())
                .foundationDate(String.valueOf(politicalPartyEntity.getFoundationDate()))
                .orientation(politicalPartyEntity.getOrientation().toString())
                .build();
    }
}
