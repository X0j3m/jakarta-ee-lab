package com.x0j3m.web.jsf.ui.politician;

import com.x0j3m.model.dto.politician.PoliticianResponseDto;
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
import java.util.UUID;

@Named
@RequestScoped
public class PoliticianJsfElement implements Serializable {
    @Inject
    private PoliticianService politicianService;

    @Getter
    @Setter
    private String id;
    @Getter
    private PoliticianResponseDto politician;

    public void init() throws IOException {
        try {
            politician = politicianService.fetchById(UUID.fromString(id));
        } catch (Exception e) {
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .responseSendError(HttpServletResponse.SC_NOT_FOUND, "Politician not found");
        }
    }
}
