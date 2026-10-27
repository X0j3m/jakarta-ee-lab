package com.x0j3m.web.jsf.ui.politicalParty;

import com.x0j3m.model.dto.politicalParty.PoliticalPartyResponseDto;
import com.x0j3m.model.entity.PoliticalPartyEntity;
import com.x0j3m.model.mapper.PoliticalPartyMapper;
import com.x0j3m.service.PoliticalPartyService;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Named
@ViewScoped
public class PoliticalPartyJsfList implements Serializable {
    private static class Navigations {
        public static final String POLITICAL_PARTIES_LIST = "politicalPartyJsfList?faces-redirect=true";
    }

    @Inject
    private PoliticalPartyService politicalPartyService;
    @Inject
    private PoliticalPartyMapper politicalPartyMapper;

    private List<PoliticalPartyResponseDto> politicalPartyList;

    public List<PoliticalPartyResponseDto> getPoliticalPartyList() {
        List<PoliticalPartyEntity> politicalPartyEntityList = politicalPartyService.fetchAll();
        politicalPartyList = politicalPartyEntityList.stream().map(politicalPartyMapper::toDto).toList();
        return politicalPartyList;
    }

    public String deletePoliticalParty(String id){
        politicalPartyService.deletePoliticalPartyById(UUID.fromString(id));
        return Navigations.POLITICAL_PARTIES_LIST;
    }
}
