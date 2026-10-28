package com.x0j3m.web.jsf.ui.politician;

import com.x0j3m.model.entity.PoliticalPartyEntity;
import com.x0j3m.model.entity.PoliticianEntity;
import com.x0j3m.service.PoliticalPartyService;
import com.x0j3m.service.PoliticianService;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Named
@ViewScoped
public class PoliticianFormJsf implements Serializable {
    @Inject
    private PoliticianService politicianService;
    @Inject
    private PoliticalPartyService politicalPartyService;

    @Getter
    @Setter
    private String id;
    @Getter
    @Setter
    private PoliticianEntity politician;

    @Getter
    private List<PoliticalPartyEntity> politicalParties;
    @Getter
    @Setter
    private String selectedPartyValue;

    @Getter
    private boolean exists;

    public String save() {
        if (selectedPartyValue != null && !selectedPartyValue.isBlank()) {
            PoliticalPartyEntity party = politicalPartyService.fetchById(UUID.fromString(selectedPartyValue));
            politician.setPoliticalParty(party);
        }

        politicianService.createPolitician(politician);

        return "/jsf/political_party__view.xhtml?id=" + politician.getPoliticalParty().getId() + "&faces-redirect=true";
    }

    public void init() {
        try {
            politician = politicianService.fetchByIdEntity(UUID.fromString(id));
            exists = true;
        } catch (Exception e) {
            politician = new PoliticianEntity();
            id = UUID.randomUUID().toString();
            politician.setId(UUID.fromString(id));
            exists = false;
        }

        politicalParties = politicalPartyService.fetchAll();
    }
}
