package com.prime.optimus.midjourneydigital.keycloak;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserDto {

    @NotBlank(message = "'username' should not be missing")
    private String username;

    @NotBlank(message = "'password' should not be missing")
    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

}
