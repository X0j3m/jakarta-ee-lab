package com.x0j3m.web.rest;

import com.x0j3m.model.dto.politicalParty.PoliticalPartyRequestDto;
import com.x0j3m.model.dto.politicalParty.PoliticalPartyResponseDto;
import com.x0j3m.service.PoliticalPartyService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Path("political-parties")
@Produces(MediaType.APPLICATION_JSON)
@NoArgsConstructor(force = true)
public class PoliticalPartyController {
    @Inject
    private PoliticalPartyService politicalPartyService;

    @GET
    public List<PoliticalPartyResponseDto> fetchAll() {
        return politicalPartyService.fetchAll();
    }

    @GET
    @Path("/{id}")
    public PoliticalPartyResponseDto fetchById(@PathParam("id") String id) {
        try{
            return politicalPartyService.fetchById(UUID.fromString(id));
        }catch (Exception e){
            throw new NotFoundException();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(PoliticalPartyRequestDto politicalPartyRequestDto) {
        politicalPartyService.createPoliticalParty(politicalPartyRequestDto);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(@PathParam("id") String id, PoliticalPartyRequestDto politicalPartyRequestDto) {
        politicalPartyService.updatePoliticalParty(UUID.fromString(id), politicalPartyRequestDto);
    }

    @DELETE
    @Path("/{id}")
    public void deleteById(@PathParam("id") String id) {
        politicalPartyService.deletePoliticalPartyById(UUID.fromString(id));
    }
}
