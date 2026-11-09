package com.x0j3m.web.jsf.converter;

import com.x0j3m.model.entity.UserEntity;
import com.x0j3m.service.UserService;
import jakarta.enterprise.context.Dependent;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

import java.util.UUID;

@Dependent
@FacesConverter(forClass = UserEntity.class, managed = true)
public class UserConverter implements Converter<UserEntity> {
    @Inject
    private UserService userService;

    @Override
    public String getAsString(FacesContext context, UIComponent component, UserEntity user) {
        if (user == null || user.getId() == null) {
            return "";
        }
        return user.getId().toString();
    }

    @Override
    public UserEntity getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return userService.fetchUserEntityById(UUID.fromString(value));
    }
}
