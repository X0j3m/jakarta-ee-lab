package com.x0j3m.web.rest;

import com.x0j3m.model.dto.politician.PoliticianRequestDto;
import com.x0j3m.model.dto.politician.PoliticianResponseDto;
import com.x0j3m.model.entity.PoliticianEntity;
import com.x0j3m.model.mapper.PoliticianMapper;
import com.x0j3m.service.PoliticianService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Path("political-parties/{politicalPartyId}/politicians")
@Produces(MediaType.APPLICATION_JSON)
@NoArgsConstructor
@Transactional
public class PoliticianController {
    @Inject
    private PoliticianService politicianService;

    @GET
    public List<PoliticianResponseDto> fetchAll(
            @PathParam("politicalPartyId") String politicalPartyId
    ) {
        return politicianService.fetchAllByPartyId(UUID.fromString(politicalPartyId));
    }

    @GET
    @Path("{id}")
    public PoliticianResponseDto fetchById(
            @PathParam("politicalPartyId") String politicalPartyId,
            @PathParam("id") String id) {
        try {
            if (politicianService.belongsToPoliticalParty(UUID.fromString(id), UUID.fromString(politicalPartyId))) {
                return politicianService.fetchById(UUID.fromString(id));
            } else {
                throw new NotFoundException();
            }
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String create(
            @PathParam("politicalPartyId") String politicalPartyId,
            PoliticianRequestDto politicianRequestDto) {
        PoliticianEntity entity = PoliticianMapper.toEntity(
                UUID.randomUUID(),
                UUID.fromString(politicalPartyId),
                politicianRequestDto
        );

        return politicianService.createPolitician(entity);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public String update(
            @PathParam("politicalPartyId") String politicalPartyId,
            @PathParam("id") String id,
            PoliticianRequestDto politicianRequestDto) {
        PoliticianEntity entity = PoliticianMapper.toEntity(
                UUID.fromString(id),
                UUID.fromString(politicalPartyId),
                politicianRequestDto
        );

        return politicianService.createPolitician(entity);
    }

    @DELETE
    @Path("/{id}")
    public void deleteById(
            @PathParam("politicalPartyId") String politicalPartyId,
            @PathParam("id") String id) {
        if (politicianService.belongsToPoliticalParty(UUID.fromString(id), UUID.fromString(politicalPartyId))) {
            politicianService.deletePoliticianById(UUID.fromString(id));
        } else {
            throw new NotFoundException();
        }
    }
}
