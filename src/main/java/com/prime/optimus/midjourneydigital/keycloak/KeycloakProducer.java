package com.prime.optimus.midjourneydigital.keycloak;

import org.keycloak.admin.client.Keycloak;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

@ApplicationScoped
public class KeycloakProducer {

    @Produces
    @ApplicationScoped
    @Named("keycloakBean")
    public Keycloak produceKeycloak() {
        // Instantiate and configure your Keycloak instance here
        // Example: Keycloak keycloak = Keycloak.getInstance("http://localhost:8080/auth", "your-realm", "client-id", "client-secret", "username", "password");
        // Return the configured Keycloak instance
        return Keycloak.getInstance(
                "http://localhost:9090",
                "master",
                "admin",
                "123",
                "optimus",
                "nSdbR7vo2dKZkwSThun4ysCpEpY7j2MH"
        );
    }
}
