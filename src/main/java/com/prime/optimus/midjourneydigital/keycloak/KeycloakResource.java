package com.prime.optimus.midjourneydigital.keycloak;

import io.quarkus.security.identity.SecurityIdentity;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.keycloak.representations.idm.UserRepresentation;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

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
    public Response createBasicUser(UserDto user) {
        this.keycloakService.createUser(user);
        return Response
                .status(Response.Status.CREATED)
                .entity("Created Keycloak user: " + user.getUsername())
                .build();
    }

}
