package com.prime.optimus.midjourneydigital.mappingenum;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@ApplicationScoped
@Path("mapping-enum")
@Tag(name = "Mapping Enum Resource")
public class MapEnumResource {

    @Inject
    MapEnumEntityRepository mapEnumEntityRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response ping() {
        mapEnumEntityRepository.persist(
                MapEnumEntity.builder().statusEnum(StatusEnum.ACTIVE).build()
        );
        return Response.ok(mapEnumEntityRepository.findAll().list()).build();
    }
}
