package com.prime.optimus.midjourneydigital.keycloak;

import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.keycloak.representations.idm.UserRepresentation;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@RegisterRestClient(configKey = "keycloak-api")
@RegisterProvider(ClientHeadersAdminRoleProvider.class)
public interface KeycloakApiClient {
    @POST
    @Path("admin/realms/master/users")
    @Produces(MediaType.APPLICATION_JSON)
    void createUser(@NotNull UserRepresentation user);
}
