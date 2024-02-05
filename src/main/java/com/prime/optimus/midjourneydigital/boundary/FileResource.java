package com.prime.optimus.midjourneydigital.boundary;

import com.prime.optimus.midjourneydigital.model.InitialLoadFileToUpload;
import com.prime.optimus.midjourneydigital.service.FileService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBodySchema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

@ApplicationScoped
@Path("files")
@Tag(name = "Working with files")
public class FileResource {

    @Inject
    FileService fileService;

    @GET
    @Path("ping")
    @Operation(summary = "Ping")
    @APIResponse(responseCode = "200", description = "Ping successfully")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "pong";
    }

    @POST
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Produces({MediaType.APPLICATION_JSON})
    @RequestBodySchema(InitialLoadFileToUpload.class)
    @APIResponses({
            @APIResponse(responseCode = "201", description = "Read file successfully"),
    })
    public Response importPeople(@Valid final @MultipartForm MultipartFormDataInput file) {
        return Response
                .status(Response.Status.CREATED)
                .entity(this.fileService.parsingPeople(file))
                .build();
    }

    @GET
    @Produces({MediaType.TEXT_PLAIN})
    @RolesAllowed({"read", "User"})
    public String getString() {
        return "hello";
    }

}
