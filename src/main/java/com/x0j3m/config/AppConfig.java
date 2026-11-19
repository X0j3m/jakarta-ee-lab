package com.x0j3m.config;

import jakarta.annotation.security.DeclareRoles;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.auth.LoginConfig;

@ApplicationPath("/api/rest")
@LoginConfig(authMethod = "MP-JWT")
@DeclareRoles({"admin", "user"})
public class AppConfig extends Application {}