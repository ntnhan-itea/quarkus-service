package com.prime.optimus.midjourneydigital.keycloak;

import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
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
