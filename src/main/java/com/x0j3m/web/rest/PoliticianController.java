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

@Path("politicians")
@Produces(MediaType.APPLICATION_JSON)
@NoArgsConstructor
@Transactional
public class PoliticianController {
    @Inject
    private PoliticianService politicianService;
    @Inject
    private PoliticianMapper politicianMapper;

    @GET
    public List<PoliticianResponseDto> fetchAll() {
        return politicianService.fetchAll();
    }

    @GET
    @Path("{id}")
    public PoliticianResponseDto fetchById(@PathParam("id") String id) {
        try {
            return politicianService.fetchById(UUID.fromString(id));
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String create(PoliticianRequestDto politicianRequestDto) {
        PoliticianEntity entity = politicianMapper.toEntity(UUID.randomUUID(), politicianRequestDto);
        return politicianService.createPolitician(entity);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public String update(@PathParam("id") String id, PoliticianRequestDto politicianRequestDto) {
        PoliticianEntity entity = politicianMapper.toEntity(UUID.fromString(id), politicianRequestDto);
        return politicianService.createPolitician(entity);
    }

    @DELETE
    @Path("/{id}")
    public void deleteById(@PathParam("id") String id) {
        politicianService.deletePoliticianById(UUID.fromString(id));
    }
}
