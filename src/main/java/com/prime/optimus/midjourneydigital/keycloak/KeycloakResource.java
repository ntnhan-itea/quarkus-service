package com.prime.optimus.midjourneydigital.keycloak;

import jakarta.annotation.security.PermitAll;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import lombok.extern.slf4j.Slf4j;

@Path("keycloak/users")
@ApplicationScoped
@Slf4j
public class KeycloakResource {

    @Inject
    KeycloakService keycloakService;

    @Context
    SecurityContext securityContext;

    @GET
    @Path("ping")
    @Produces(MediaType.TEXT_PLAIN)
    @PermitAll
    public String ping() {
        return "pong";
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
//    @RolesAllowed("basic-user")
    public Response createBasicUser(@Valid UserDto user) {
        this.keycloakService.createUser(user);
        return Response
                .status(Response.Status.CREATED)
                .entity("Created Keycloak user: " + user.getUsername())
                .build();
    }

}
