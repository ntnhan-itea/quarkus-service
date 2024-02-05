package com.prime.optimus.midjourneydigital.model;

import jakarta.ws.rs.FormParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

import java.io.InputStream;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InitialLoadFileToUpload {

    @Schema(name = "file", type = SchemaType.STRING, format = "binary", description = "Csv file upload")
    @FormParam("file")
    @PartType("test/csv")
    private InputStream file;
}
