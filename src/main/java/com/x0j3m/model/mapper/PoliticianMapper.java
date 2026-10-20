package com.x0j3m.model.mapper;

import com.x0j3m.model.dto.politician.PoliticianRequestDto;
import com.x0j3m.model.dto.politician.PoliticianResponseDto;
import com.x0j3m.model.entity.PoliticalPartyEntity;
import com.x0j3m.model.entity.PoliticianEntity;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@ApplicationScoped
@NoArgsConstructor
public class PoliticianMapper {
    public PoliticianEntity toEntity(UUID id, PoliticianRequestDto politicianDto) {
        return PoliticianEntity.builder()
                .id(id)
                .name(politicianDto.name())
                .surname(politicianDto.surname())
                .birthDate(LocalDate.parse(politicianDto.birthDate()))
                .constituency(Integer.parseInt(politicianDto.constituency()))
                .politicalParty(PoliticalPartyEntity.builder().id(UUID.fromString(politicianDto.politicalParty().id())).build())
                .build();
    }

    public PoliticianResponseDto toDto(PoliticianEntity politicianEntity) {
        return PoliticianResponseDto.builder()
                .id(String.valueOf(politicianEntity.getId()))
                .name(politicianEntity.getName())
                .surname(politicianEntity.getSurname())
                .birthDate(String.valueOf(politicianEntity.getBirthDate()))
                .constituency(String.valueOf(politicianEntity.getConstituency()))
                .politicalParty(String.valueOf(politicianEntity.getPoliticalParty().getId()))
                .build();
    }
}
