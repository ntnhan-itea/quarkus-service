package com.prime.optimus.midjourneydigital.keycloak;

import io.quarkus.security.Authenticated;
import org.keycloak.representations.idm.UserRepresentation;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("keycloak/users")
@ApplicationScoped
public class KeycloakResource {

    @GET
    @Path("ping")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed("admin1")
    public String ping() {
        return "alive";
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(UserRepresentation user) {
        return Response.status(Response.Status.CREATED)
//                .entity(keycloak.realm("master").users().create(user))
                .build();
    }
}
