package com.prime.optimus.midjourneydigital.keycloak;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("keycloak/users")
@ApplicationScoped
public class KeycloakResource {

    @Inject
    @RestClient
    KeycloakApiClient keycloakApiClient;

    @Inject
    Keycloak keycloak;

//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response createUser(UserDto user, @HeaderParam("Authorization") String authorization) {
////        String accessToken = "Bearer YOUR_ACCESS_TOKEN"; // Obtain the access token from Keycloak
//
//        keycloakApiClient.createUser(user, "master", authorization);
//
//        return Response.status(Response.Status.CREATED).build();
//    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(UserRepresentation user) {
        keycloak.realm("MyRealm").users().create(user);
        return Response.status(Response.Status.CREATED).build();
    }
}
