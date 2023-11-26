package com.prime.optimus.midjourneydigital.keycloak;

import com.prime.optimus.midjourneydigital.properties.PropertiesService;
import io.quarkus.security.identity.SecurityIdentity;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.keycloak.representations.IDToken;
import org.keycloak.representations.idm.UserRepresentation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Form;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.keycloak.OAuth2Constants.ACCESS_TOKEN;
import static org.keycloak.OAuth2Constants.CLIENT_ID;
import static org.keycloak.OAuth2Constants.CLIENT_SECRET;
import static org.keycloak.OAuth2Constants.GRANT_TYPE;
import static org.keycloak.OAuth2Constants.PASSWORD;
import static org.keycloak.OAuth2Constants.TOKEN_TYPE;
import static org.keycloak.OAuth2Constants.USERNAME;

@ApplicationScoped
@Slf4j
public class KeycloakService {
    public static final String BEARER_TOKEN_TYPE = "Bearer";

    @Inject
    @RestClient
    KeycloakApiClient keycloakApiClient;

    @Inject
    @RestClient
    KeycloakAdminClient keycloakAdminClient;

    @Inject
    PropertiesService propertiesService;

    @Inject
    KeycloakMapper KeycloakMapper;

    @Inject
    SecurityIdentity securityIdentity;

    @Inject
    JsonWebToken jwt;

    public TokenDto getAdminTokenDto() {
        Form keyCloak = new Form();
        keyCloak.param(USERNAME, propertiesService.getManageUserKeycloakUsr());
        keyCloak.param(PASSWORD, propertiesService.getManageUserKeycloakPwd());
        keyCloak.param(CLIENT_ID, propertiesService.getKeycloakClientId());
        keyCloak.param(CLIENT_SECRET, propertiesService.getKeycloakSecret());
        keyCloak.param(GRANT_TYPE, PASSWORD);

        Map<String, String> token = this.keycloakAdminClient.getToken(keyCloak);

        return TokenDto.builder()
                .token(token.get(ACCESS_TOKEN))
                .type(token.get(TOKEN_TYPE))
                .build();
    }

    public String getAdminToken() {
        final TokenDto tokenDto = getAdminTokenDto();
        return tokenDto.getType() + SPACE + tokenDto.getToken();
    }

    public String getTokenHeader() {
        return BEARER_TOKEN_TYPE + SPACE + this.jwt.getRawToken();
    }

    public String getFirstName() {
        return jwt.getClaim(Claims.given_name);
    }

    public String getLastName() {
        return jwt.getClaim(Claims.family_name);
    }

    public boolean hasEmailVerified() {
        return jwt.getClaim(Claims.email_verified);
    }

    public String getUserName() {
        return securityIdentity.getPrincipal().getName();
    }

    public void createUser(UserDto user) {
        try {
            UserRepresentation userRepresentation = KeycloakMapper.toUserRepresentation(user);
            userRepresentation.setGroups(List.of(propertiesService.getKeycloakGroupDefault()));
            userRepresentation.setRealmRoles(List.of(propertiesService.getKeycloakRealmRoleBasic()));
            userRepresentation.setEnabled(true);
            this.keycloakApiClient.createUser(userRepresentation);

            log.info(
                    "Created keycloak user: username [{}], group {}, email [{}]",
                    userRepresentation.getUsername(),
                    userRepresentation.getGroups(),
                    userRepresentation.getEmail()
            );
        } catch (Exception e) {
            log.error("Cannot create Keycloak user [{}]: {}", user.getUsername(), e.getMessage());
            throw e;
        }
    }

}
