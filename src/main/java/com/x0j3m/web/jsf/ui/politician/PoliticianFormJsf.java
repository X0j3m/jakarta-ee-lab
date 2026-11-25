package com.x0j3m.web.jsf.ui.politician;

import com.x0j3m.model.entity.PoliticalPartyEntity;
import com.x0j3m.model.entity.PoliticianEntity;
import com.x0j3m.model.entity.UserEntity;
import com.x0j3m.security.PermissionsChecker;
import com.x0j3m.service.PoliticalPartyService;
import com.x0j3m.service.PoliticianService;
import com.x0j3m.service.UserService;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.SecurityContext;
import jakarta.ws.rs.NotFoundException;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Named
@ViewScoped
public class PoliticianFormJsf implements Serializable {
    @Inject
    private PoliticianService politicianService;
    @Inject
    private PoliticalPartyService politicalPartyService;
    @Inject
    private UserService userService;

    @Inject
    private SecurityContext securityContext;

    @Getter
    @Setter
    private String id;
    @Getter
    @Setter
    private PoliticianEntity politician;

    @Getter
    private List<PoliticalPartyEntity> politicalParties;

    @Getter
    private List<UserEntity> users;

    @Getter
    private boolean exists;

    @Getter
    @Setter
    private UUID selectedUserId;

    public void init() {
        try {
            politician = politicianService.fetchByIdEntity(UUID.fromString(id));
            exists = true;
            if (!PermissionsChecker.checkIsAdminOrOwner(securityContext, politician.getUser().getUsername())) {
                throw new NotFoundException();
            }
        } catch (Exception e) {
            politician = new PoliticianEntity();
            id = UUID.randomUUID().toString();
            politician.setId(UUID.fromString(id));
            exists = false;
        }

        politicalParties = politicalPartyService.fetchAll();
        users = userService.fetchAllEntities();

        if (politician.getUser() == null) {
            Principal principal = securityContext != null
                    ? securityContext.getCallerPrincipal()
                    : FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();

            if (principal != null) {
                String loggedUsername = principal.getName();

                users.stream()
                        .filter(u -> u.getUsername().equals(loggedUsername))
                        .findFirst()
                        .ifPresent(politician::setUser);
            }
        }

        if (politician.getUser() != null) {
            this.selectedUserId = politician.getUser().getId();
        }
    }

    public String save() {
        try {
            politicianService.createPolitician(politician);
        } catch (Exception ignored) {
        }

        if (selectedUserId != null) {
            UserEntity user = userService.fetchUserEntityById(selectedUserId);
            politician.setUser(user);
        }

        return "/jsf/political_party__view.xhtml?id=" + politician.getPoliticalParty().getId() + "&faces-redirect=true";
    }

    public boolean canEditOwner() {
        return PermissionsChecker.checkIsAdmin(securityContext);
    }
}