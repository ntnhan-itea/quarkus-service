package com.prime.optimus.midjourneydigital.keycloak;


import jakarta.inject.Inject;
import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;

import java.io.IOException;

public class ClientHeadersAdminRoleProvider implements ClientRequestFilter {
    public static final String AUTHORIZATION = "Authorization";

    @Inject
    KeycloakService keycloakService;

    @Override
    public void filter(ClientRequestContext clientRequestContext) throws IOException {
        clientRequestContext.getHeaders().remove(AUTHORIZATION);
        clientRequestContext.getHeaders().add(AUTHORIZATION, keycloakService.getAdminToken());
    }
}
