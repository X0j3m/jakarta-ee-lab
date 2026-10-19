package com.x0j3m.service;

import com.x0j3m.data.PoliticalPartyRepository;
import com.x0j3m.model.dto.politicalParty.PoliticalPartyRequestDto;
import com.x0j3m.model.dto.politicalParty.PoliticalPartyResponseDto;
import com.x0j3m.model.entity.PoliticalPartyEntity;
import com.x0j3m.model.mapper.PoliticalPartyMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@NoArgsConstructor(force = true)
public class PoliticalPartyService {
    @Inject
    private PoliticalPartyRepository politicalPartyRepository;
    @Inject
    private PoliticalPartyMapper politicalPartyMapper;

    public List<PoliticalPartyResponseDto> fetchAll() {
        return politicalPartyRepository.getAll()
                .stream()
                .map(politicalPartyMapper::toDto)
                .toList();
    }

    public PoliticalPartyResponseDto fetchById(UUID id) {
        Optional<PoliticalPartyEntity> politicalParty = politicalPartyRepository.getById(id);
        return politicalParty.map(politicalPartyMapper::toDto).orElseThrow();
    }

    public String createPoliticalParty(PoliticalPartyRequestDto politicalPartyDto) {
        PoliticalPartyEntity politicalParty = politicalPartyMapper.toEntity(UUID.randomUUID(), politicalPartyDto);
        politicalPartyRepository.save(politicalParty);
        return politicalParty.getId().toString();
    }

    public String updatePoliticalParty(UUID id, PoliticalPartyRequestDto politicalPartyDto) {
        PoliticalPartyEntity politicalParty = politicalPartyMapper.toEntity(id, politicalPartyDto);
        politicalPartyRepository.save(politicalParty);
        return politicalParty.getId().toString();
    }

    public void deletePoliticalPartyById(UUID id) {
        politicalPartyRepository.delete(id);
    }

    public boolean existsById(UUID id) {
        return politicalPartyRepository.getById(id).isPresent();
    }
}
