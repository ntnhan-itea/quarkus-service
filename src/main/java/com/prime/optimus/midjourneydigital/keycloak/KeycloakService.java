package com.prime.optimus.midjourneydigital.keycloak;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.keycloak.representations.idm.UserRepresentation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Form;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.SPACE;

@ApplicationScoped
public class KeycloakService {
    @Inject
    @RestClient
    KeycloakApiClient keycloakApiClient;

    @Inject
    @RestClient
    KeycloakAdminClient keycloakAdminClient;

    @Inject
    JsonWebToken jwt;

    public TokenDto getAdminTokenDto() {
        Form keyCloak = new Form();
        keyCloak.param("username", "admin");
        keyCloak.param("password", "admin");
        keyCloak.param("grant_type", "password");
        keyCloak.param("client_id", "optimus");
        keyCloak.param("client_secret", "2StEWWjNYWzk6SNZSNaYdWA49zSqcx4X");
        Map<String, String> token = this.keycloakAdminClient.getToken(keyCloak);

        return TokenDto.builder()
                .token(token.get("access_token"))
                .type(token.get("token_type"))
                .build();
    }

    public String getAdminToken() {
        final TokenDto tokenDto = getAdminTokenDto();
        return tokenDto.getType() + SPACE + tokenDto.getToken();
    }

    public String getTokenHeader() {
        return "Bearer" + SPACE + this.jwt.getRawToken();
    }

    public void createUser(UserRepresentation user) {
        this.keycloakApiClient.createUser(user);
    }

}
