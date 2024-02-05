package com.prime.optimus.midjourneydigital.audit;

import com.prime.optimus.midjourneydigital.keycloak.KeycloakService;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class AuditListener {
    private KeycloakService keycloakService;

    private KeycloakService getKeycloakService() {
        if (keycloakService == null) {
            keycloakService = CDI.current().select(KeycloakService.class).get();
        }
        return keycloakService;
    }

    @PrePersist
    public <T extends Audit> void onPrePersist(T entity) {
        entity.setCreationTime(LocalDateTime.now());
        entity.setModificationUser(getKeycloakService().getUserName());
    }

    @PreUpdate
    public <T extends Audit> void onPreUpdate(T entity) {
        entity.setModificationTime(LocalDateTime.now());
        entity.setModificationUser(getKeycloakService().getUserName());
    }
}
