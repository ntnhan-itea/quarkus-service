package com.prime.optimus.midjourneydigital.keycloak;


import javax.inject.Inject;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.ext.Provider;
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
