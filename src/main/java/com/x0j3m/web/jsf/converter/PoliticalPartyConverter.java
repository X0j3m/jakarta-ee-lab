package com.x0j3m.web.jsf.converter;

import com.x0j3m.model.entity.PoliticalPartyEntity;
import com.x0j3m.service.PoliticalPartyService;
import jakarta.enterprise.context.Dependent;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

import java.util.UUID;

@Dependent
@FacesConverter(forClass = PoliticalPartyEntity.class, managed = true)
public class PoliticalPartyConverter implements Converter<PoliticalPartyEntity> {

    @Inject
    private PoliticalPartyService politicalPartyService;

    @Override
    public String getAsString(FacesContext context, UIComponent component, PoliticalPartyEntity party) {
        if (party == null || party.getId() == null) {
            return "";
        }
        return party.getId().toString();
    }

    @Override
    public PoliticalPartyEntity getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return politicalPartyService.fetchById(UUID.fromString(value));
    }
}