package com.x0j3m.service;

import com.x0j3m.data.PoliticianRepository;
import com.x0j3m.model.dto.politician.PoliticianResponseDto;
import com.x0j3m.model.entity.PoliticianEntity;
import com.x0j3m.model.mapper.PoliticianMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;
import jakarta.ws.rs.NotFoundException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.x0j3m.security.PermissionsChecker.checkIsAdmin;
import static com.x0j3m.security.PermissionsChecker.checkIsAdminOrOwner;

@ApplicationScoped
@NoArgsConstructor
@Slf4j
public class PoliticianService {
    @Inject
    private PoliticianRepository politicianRepository;
    @Inject
    private SecurityContext securityContext;

    public List<PoliticianResponseDto> fetchAll() {
        return politicianRepository.getAll()
                .stream()
                .map(PoliticianMapper::toDto)
                .toList();
    }

    public PoliticianResponseDto fetchById(UUID id) {
        Optional<PoliticianEntity> politician = politicianRepository.getById(id);
        if (politician.isPresent()) {
            String ownerName = politician.get().getUser().getUsername();
            if (checkIsAdminOrOwner(securityContext, ownerName)) {
                return politician.map(PoliticianMapper::toDto).orElseThrow();
            }
            throw new NotFoundException();
        }
        throw new NotFoundException();
    }

    public PoliticianEntity fetchByIdEntity(UUID id) {
        Optional<PoliticianEntity> politician = politicianRepository.getById(id);
        return politician.orElseThrow();
    }

    public List<PoliticianEntity> fetchAllByPartyIdEntity(UUID id) {
        if (checkIsAdmin(securityContext)) {
            return politicianRepository.getAllByPartyId(id);
        }
        return politicianRepository.getAllByPartyIdAndUsername(id, securityContext.getCallerPrincipal().getName());
    }

    public List<PoliticianResponseDto> fetchAllByPartyId(UUID id) {
        if (checkIsAdmin(securityContext)) {
            return politicianRepository.getAllByPartyId(id)
                    .stream()
                    .map(PoliticianMapper::toDto)
                    .toList();
        }
        return politicianRepository.getAllByPartyIdAndUsername(id, securityContext.getCallerPrincipal().getName())
                .stream()
                .map(PoliticianMapper::toDto)
                .toList();
    }

    public String createPolitician(PoliticianEntity politicianEntity) {
        String userName = securityContext.getCallerPrincipal().getName();

        String op = existsById(politicianEntity.getId()) ? "UPDATE" : "CREATE";

        if (checkIsAdminOrOwner(securityContext, politicianEntity.getUser().getUsername())) {
            politicianRepository.save(politicianEntity);
            String message = userName + " " + op + " politician ID: " + politicianEntity.getId();
            log.info(message);
            return politicianEntity.getId().toString();
        } else {
            throw new NotFoundException();
        }
    }

    public void deletePoliticianById(UUID id) {
        String userName = securityContext.getCallerPrincipal().getName();

        if (existsById(id)) {
            if (checkIsAdminOrOwner(securityContext, getOwner(id))) {
                politicianRepository.delete(id);
                String message = userName + " DELETE politician ID: " + id;
                log.info(message);
            }
        }
    }

    public boolean belongsToPoliticalParty(UUID id, UUID politicalPartyId) {
        return politicianRepository.belongsToPoliticalParty(id, politicalPartyId);
    }

    private String getOwner(UUID id) {
        Optional<PoliticianEntity> politician = politicianRepository.getById(id);
        return politician.map(politicianEntity -> politicianEntity.getUser().getUsername()).orElse(null);
    }

    public UUID getOwnerId(UUID id) {
        Optional<PoliticianEntity> politician = politicianRepository.getById(id);
        return politician.map(politicianEntity -> politicianEntity.getUser().getId()).orElse(null);
    }

    public boolean existsById(UUID id) {
        return politicianRepository.getById(id).isPresent();
    }
}