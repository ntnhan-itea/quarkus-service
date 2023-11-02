package com.prime.optimus.midjourneydigital.keycloak;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserDto {
    private String username;
    private String email;
    private boolean enabled;
    private List<CredentialDto> credentials;
}
