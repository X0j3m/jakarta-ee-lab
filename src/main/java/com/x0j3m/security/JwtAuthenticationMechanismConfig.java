package com.x0j3m.security;

import jakarta.enterprise.context.ApplicationScoped;

import java.net.URI;

@ApplicationScoped
public class JwtAuthenticationMechanismConfig extends JwtAuthenticationMechanism {

    public JwtAuthenticationMechanismConfig() {
        super(
                "http://localhost:9000",
                    "jakarta-client",
                URI.create("http://localhost:9000/oauth2/jwks")
        );
    }

}
