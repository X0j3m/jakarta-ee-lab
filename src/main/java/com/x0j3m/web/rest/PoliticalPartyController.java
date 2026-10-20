package com.x0j3m.web.rest;

import com.x0j3m.model.dto.politicalParty.PoliticalPartyRequestDto;
import com.x0j3m.model.dto.politicalParty.PoliticalPartyResponseDto;
import com.x0j3m.model.entity.PoliticalPartyEntity;
import com.x0j3m.model.mapper.PoliticalPartyMapper;
import com.x0j3m.service.PoliticalPartyService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Path("political-parties")
@Produces(MediaType.APPLICATION_JSON)
@NoArgsConstructor(force = true)
@Transactional
public class PoliticalPartyController {
    @Inject
    private PoliticalPartyService politicalPartyService;
    @Inject
    private PoliticalPartyMapper politicalPartyMapper;

    @GET
    public List<PoliticalPartyResponseDto> fetchAll() {
        return politicalPartyService.fetchAll().stream()
                .map(politicalPartyMapper::toDto)
                .toList();
    }

    @GET
    @Path("/{id}")
    public PoliticalPartyResponseDto fetchById(@PathParam("id") String id) {
        try {
            PoliticalPartyEntity entity = politicalPartyService.fetchById(UUID.fromString(id));
            return politicalPartyMapper.toDto(entity);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String create(PoliticalPartyRequestDto politicalPartyRequestDto) {
        PoliticalPartyEntity entity = politicalPartyMapper.toEntity(UUID.randomUUID(), politicalPartyRequestDto);
        return politicalPartyService.createPoliticalParty(entity);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public String update(@PathParam("id") String id, PoliticalPartyRequestDto politicalPartyRequestDto) {
        PoliticalPartyEntity entity = politicalPartyMapper.toEntity(UUID.fromString(id), politicalPartyRequestDto);
        return politicalPartyService.createPoliticalParty(entity);
    }

    @DELETE
    @Path("/{id}")
    public void deleteById(@PathParam("id") String id) {
        politicalPartyService.deletePoliticalPartyById(UUID.fromString(id));
    }
}
