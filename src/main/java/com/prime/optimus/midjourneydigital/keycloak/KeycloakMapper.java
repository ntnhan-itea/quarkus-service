package com.prime.optimus.midjourneydigital.keycloak;

import org.apache.commons.lang3.StringUtils;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.keycloak.OAuth2Constants.SCOPE_PHONE;
import static org.keycloak.representations.idm.CredentialRepresentation.PASSWORD;
import static org.mapstruct.CollectionMappingStrategy.ADDER_PREFERRED;
import static org.mapstruct.MappingConstants.ComponentModel.CDI;

@Mapper(
        componentModel = CDI,
        collectionMappingStrategy = ADDER_PREFERRED,
        imports = {LocalDate.class}
)
public interface KeycloakMapper {

    @Mapping(target = "attributes", source = "phone", qualifiedByName = "toAttributes")
    @Mapping(target = "credentials", source = "password", qualifiedByName = "toCredentials")
    UserRepresentation toUserRepresentation(UserDto userDto);

    @Named("toAttributes")
    default Map<String, List<String>> toAttributes(String phone) {
        Map<String, List<String>> attributes = new HashMap<>();

        if (StringUtils.isNotBlank(phone)) {
            attributes.put(SCOPE_PHONE, List.of(phone.trim()));
        }

        return attributes;
    }

    @Named("toCredentials")
    default List<CredentialRepresentation> toCredentials(String password) {

        CredentialRepresentation representation = new CredentialRepresentation();
        representation.setType(PASSWORD);
        representation.setTemporary(false);
        representation.setValue(password.trim());

        return Collections.singletonList(representation);
    }


}
