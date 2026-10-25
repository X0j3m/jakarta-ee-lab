package com.x0j3m.web.jsf;

import com.x0j3m.model.dto.politicalParty.PoliticalPartyResponseDto;
import com.x0j3m.model.dto.politician.PoliticianResponseDto;
import com.x0j3m.model.entity.PoliticalPartyEntity;
import com.x0j3m.model.mapper.PoliticalPartyMapper;
import com.x0j3m.service.PoliticalPartyService;
import com.x0j3m.service.PoliticianService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Named
@RequestScoped
public class PoliticalPartyJsfElement implements Serializable {
    private static class Navigations {
        public static final String POLITICAL_PARTY = "politicalPartyJsfElement?faces-redirect=true";
    }
    @Inject
    private PoliticalPartyService politicalPartyService;
    @Inject
    private PoliticianService politicianService;
    @Inject
    private PoliticalPartyMapper politicalPartyMapper;

    @Getter
    @Setter
    private String id;
    @Getter
    private PoliticalPartyResponseDto politicalParty;
    @Getter
    private List<PoliticianResponseDto> politicians;

    public void init() throws IOException {
        try {
            PoliticalPartyEntity politicalPartyEntity = politicalPartyService.fetchById(UUID.fromString(id));
            politicalParty = politicalPartyMapper.toDto(politicalPartyEntity);
            politicians = politicianService.fetchAllByPartyId(UUID.fromString(id));
        }catch (Exception e){
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .responseSendError(HttpServletResponse.SC_NOT_FOUND, "Political party not found");
        }
    }

    public String deletePolitician(String id){
        politicianService.deletePoliticianById(UUID.fromString(id));
        return Navigations.POLITICAL_PARTY;
    }
}
