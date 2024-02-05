package com.prime.optimus.midjourneydigital.properties;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.Getter;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
@Getter
public class PropertiesService {

    @ConfigProperty(name = "keycloak.manage.user.username")
    String manageUserKeycloakUsr;

    @ConfigProperty(name = "keycloak.manage.user.password")
    String manageUserKeycloakPwd;

    @ConfigProperty(name = "quarkus.oidc.client-id")
    String keycloakClientId;

    @ConfigProperty(name = "quarkus.oidc.credentials.secret")
    String keycloakSecret;

    @ConfigProperty(name = "keycloak.group.default.user.basic")
    String keycloakGroupDefault;

    @ConfigProperty(name = "keycloak.realm.role.basic")
    String keycloakRealmRoleBasic;

}
