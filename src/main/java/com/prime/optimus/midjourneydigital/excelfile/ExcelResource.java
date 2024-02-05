package com.prime.optimus.midjourneydigital.excelfile;

import jakarta.annotation.security.PermitAll;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.Arrays;

@ApplicationScoped
@Path("excel")
@Tag(name = "Excel file resource")
public class ExcelResource {

    @Inject
    ExcelService excelService;

    @GET
    @Produces("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    @PermitAll
    public Response downloadExcelFile() {
        ExcelDto excelDto = new ExcelDto("1", " Nhan Nguyen");

        return Response
                .ok(
                        excelService.generateExcelFromTemplate(
                                Arrays.asList(excelDto, excelDto, excelDto)
                        )
                )
                .header("Content-Disposition", "attachment; filename=\"file.xlsx\"")
                .build();
    }
}
