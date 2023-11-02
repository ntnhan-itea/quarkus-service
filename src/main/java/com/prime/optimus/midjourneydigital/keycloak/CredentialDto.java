package com.prime.optimus.midjourneydigital.keycloak;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CredentialDto {
    private String type;
    private String value;
    private boolean temporary;
}
