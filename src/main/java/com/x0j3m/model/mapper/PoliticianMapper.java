package com.x0j3m.model.mapper;

import com.x0j3m.model.dto.politician.PoliticianRequestDto;
import com.x0j3m.model.dto.politician.PoliticianResponseDto;
import com.x0j3m.model.entity.PoliticalPartyEntity;
import com.x0j3m.model.entity.PoliticianEntity;
import com.x0j3m.model.entity.UserEntity;

import java.time.LocalDate;
import java.util.UUID;

public class PoliticianMapper {
    public static PoliticianEntity toEntity(UUID id, UUID politicalPartyId, PoliticianRequestDto politicianDto) {
        return PoliticianEntity.builder()
                .id(id)
                .name(politicianDto.name())
                .surname(politicianDto.surname())
                .birthDate(LocalDate.parse(politicianDto.birthDate()))
                .constituency(Integer.parseInt(politicianDto.constituency()))
                .politicalParty(PoliticalPartyEntity.builder().id(politicalPartyId).build())
                .user(UserEntity.builder().id(UUID.fromString(politicianDto.userId())).build())
                .build();
    }

    public static PoliticianResponseDto toDto(PoliticianEntity politicianEntity) {
        return PoliticianResponseDto.builder()
                .id(String.valueOf(politicianEntity.getId()))
                .name(politicianEntity.getName())
                .surname(politicianEntity.getSurname())
                .birthDate(String.valueOf(politicianEntity.getBirthDate()))
                .constituency(String.valueOf(politicianEntity.getConstituency()))
                .politicalParty(String.valueOf(politicianEntity.getPoliticalParty().getId()))
                .user(String.valueOf(politicianEntity.getUser().getId()))
                .build();
    }
}
