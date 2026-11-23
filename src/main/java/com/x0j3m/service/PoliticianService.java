package com.x0j3m.service;

import com.x0j3m.data.PoliticianRepository;
import com.x0j3m.model.dto.politician.PoliticianResponseDto;
import com.x0j3m.model.dto.user.UserResponseDto;
import com.x0j3m.model.entity.PoliticianEntity;
import com.x0j3m.model.mapper.PoliticianMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.AuthenticationException;
import jakarta.security.enterprise.SecurityContext;
import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.NotFoundException;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.x0j3m.security.PermissionsChecker.checkIsAdmin;
import static com.x0j3m.security.PermissionsChecker.checkIsAdminOrOwner;

@ApplicationScoped
@NoArgsConstructor
public class PoliticianService {
    @Inject
    private PoliticianRepository politicianRepository;
    @Inject
    private UserService userService;
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

    public List<PoliticianResponseDto> fetchAllByPartyId(UUID id) {
        if (checkIsAdmin(securityContext)) {
            return politicianRepository.getAllByPartyId(id)
                    .stream()
                    .map(PoliticianMapper::toDto)
                    .toList();
        }
        String callerName = securityContext.getCallerPrincipal().getName();
        return politicianRepository.getAllByPartyIdAndUsername(id, callerName)
                .stream()
                .map(PoliticianMapper::toDto)
                .toList();
    }

    public String createPolitician(PoliticianEntity politicianEntity) {
        UserResponseDto owner = userService.fetchUserById(politicianEntity.getUser().getId());

        if (checkIsAdminOrOwner(securityContext, owner.username())) {
            politicianRepository.save(politicianEntity);
            return politicianEntity.getId().toString();
        } else {
            throw new NotFoundException();
        }
    }

    public void deletePoliticianById(UUID id) {
        if (existsById(id)) {
            if (checkIsAdminOrOwner(securityContext, getOwner(id))) {
                politicianRepository.delete(id);
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