package com.x0j3m.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.authentication.mechanism.http.OpenIdAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.openid.ClaimsDefinition;

@ApplicationScoped
@OpenIdAuthenticationMechanismDefinition(
        providerURI = "http://localhost:9000",
        clientId = "jakarta-client",
        clientSecret = "secret",
        redirectURI = "${baseURL}/callback",
        scope = {"openid", "profile"},
        claimsDefinition = @ClaimsDefinition(
                callerNameClaim = "sub",
                callerGroupsClaim = "groups"
        ),
        redirectToOriginalResource = true
)
public class SecurityConfig {}