package com.prime.optimus.midjourneydigital.mappingenum;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
