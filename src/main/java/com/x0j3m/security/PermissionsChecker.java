package com.x0j3m.security;

import jakarta.security.enterprise.SecurityContext;

public class PermissionsChecker {
    public static boolean checkIsAdmin(SecurityContext securityContext) {
        return securityContext.isCallerInRole("admin");
    }

    public static boolean checkIsOwner(SecurityContext securityContext, String ownerName) {
        if (securityContext.isCallerInRole("user") || securityContext.isCallerInRole("admin")) {
            return securityContext.getCallerPrincipal().getName().equals(ownerName);
        }
        return false;
    }

    public static boolean checkIsAdminOrOwner(SecurityContext securityContext, String ownerName) {
        return checkIsAdmin(securityContext) || checkIsOwner(securityContext, ownerName);
    }
}
