package com.prime.optimus.midjourneydigital.keycloak;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.Map;


@RegisterRestClient(configKey = "keycloak-api")
@Path("realms/master")
public interface KeycloakAdminClient {
    @POST
    @Path("protocol/openid-connect/token")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    Map<String, String> getToken(Form formBody);
}
