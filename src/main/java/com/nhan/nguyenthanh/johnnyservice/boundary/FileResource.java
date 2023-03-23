package com.nhan.nguyenthanh.johnnyservice.boundary;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.nhan.nguyenthanh.johnnyservice.model.InitialLoadFileToUpload;
import com.nhan.nguyenthanh.johnnyservice.model.Person;
import com.nhan.nguyenthanh.johnnyservice.service.FileService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBodySchema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import java.util.List;
import java.util.Map;

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

}
