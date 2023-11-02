package com.prime.optimus.midjourneydigital.keycloak;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@RegisterRestClient(configKey = "keycloak-api")
public interface KeycloakApiClient {

    @POST
    @Path("/admin/realms/{realm}/users")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    void createUser(UserDto user,
                    @PathParam("realm") String realm,
                    @HeaderParam("Authorization") String authorization);

}
