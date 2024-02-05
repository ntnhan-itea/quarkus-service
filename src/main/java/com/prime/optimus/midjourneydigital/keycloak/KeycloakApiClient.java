package com.prime.optimus.midjourneydigital.keycloak;

import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.keycloak.representations.idm.UserRepresentation;

@RegisterRestClient(configKey = "keycloak-api")
@RegisterProvider(ClientHeadersAdminRoleProvider.class)
public interface KeycloakApiClient {
    @POST
    @Path("admin/realms/master/users")
    @Produces(MediaType.APPLICATION_JSON)
    void createUser(@NotNull UserRepresentation user);
}
