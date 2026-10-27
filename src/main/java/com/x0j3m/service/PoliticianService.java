package com.x0j3m.service;

import com.x0j3m.data.PoliticianRepository;
import com.x0j3m.model.dto.politician.PoliticianRequestDto;
import com.x0j3m.model.dto.politician.PoliticianResponseDto;
import com.x0j3m.model.entity.PoliticalPartyEntity;
import com.x0j3m.model.entity.PoliticianEntity;
import com.x0j3m.model.mapper.PoliticalPartyMapper;
import com.x0j3m.model.mapper.PoliticianMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@NoArgsConstructor
public class PoliticianService {
    @Inject
    private PoliticianRepository politicianRepository;
    @Inject
    private PoliticalPartyService politicalPartyService;
    @Inject
    private PoliticianMapper politicianMapper;
    @Inject
    private PoliticalPartyMapper politicalPartyMapper;

    public List<PoliticianResponseDto> fetchAll() {
        return politicianRepository.getAll()
                .stream()
                .map(politicianMapper::toDto)
                .toList();
    }

    public PoliticianResponseDto fetchById(UUID id) {
        Optional<PoliticianEntity> politicalParty = politicianRepository.getById(id);
        return politicalParty.map(politicianMapper::toDto).orElseThrow();
    }

    public PoliticianEntity fetchByIdEntity(UUID id) {
        Optional<PoliticianEntity> politicalParty = politicianRepository.getById(id);
        return politicalParty.orElseThrow();
    }

    public List<PoliticianResponseDto> fetchAllByPartyId(UUID id) {
        return politicianRepository.getAllByPartyId(id)
                .stream()
                .map(politicianMapper::toDto)
                .toList();
    }

    public String createPolitician(PoliticianEntity politicianEntity) {
        politicianRepository.save(politicianEntity);
        return politicianEntity.getId().toString();
    }

    public void deletePoliticianById(UUID id) {
        politicianRepository.delete(id);
    }

    public boolean existsById(UUID id) {
        return politicianRepository.getById(id).isPresent();
    }
}