package com.prime.optimus.midjourneydigital.keycloak;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("keycloak/users")
@ApplicationScoped
public class KeycloakResource {
    @Inject
    @Named("keycloakBean")
      Keycloak keycloak;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(UserRepresentation user) {
        keycloak.realm("MyRealm").users().create(user);
        return Response.status(Response.Status.CREATED).build();
    }
}
