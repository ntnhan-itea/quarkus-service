package com.nhan.nguyenthanh.johnnyservice.boundary;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@ApplicationScoped
@Path("files")
@Tag(name = "Working with files")
public class FileResource {

    @GET
    @Path("ping")
    @Operation(summary = "Ping")
    @APIResponse(responseCode = "200", description = "Ping successfully")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "pong";
    }


}
