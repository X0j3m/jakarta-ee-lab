package com.x0j3m.web.jsf.ui.politicalParty;

import com.x0j3m.model.entity.Orientation;
import com.x0j3m.model.entity.PoliticalPartyEntity;
import com.x0j3m.service.PoliticalPartyService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Named
@RequestScoped
public class PoliticalPartyFormJsf {
    @Inject
    private PoliticalPartyService politicalPartyService;

    @Getter
    @Setter
    private String id;
    @Getter
    @Setter
    private PoliticalPartyEntity politicalParty = null;
    @Getter
    private boolean exists;

    @Getter
    private List<Orientation> orientations = Orientation.getOrientations();

    public void init() {
        try {
            PoliticalPartyEntity politicalPartyEntity = politicalPartyService.fetchById(UUID.fromString(id));

            exists = politicalPartyEntity != null;
            politicalParty = politicalPartyEntity;

            if (orientations == null) {
                orientations = Orientation.getOrientations();
            }
        } catch (Exception e) {
            exists = false;
            politicalParty = new PoliticalPartyEntity();
            politicalParty.setId(UUID.randomUUID());
            politicalParty.setOrientation(orientations.get(0));
        }
    }
}
