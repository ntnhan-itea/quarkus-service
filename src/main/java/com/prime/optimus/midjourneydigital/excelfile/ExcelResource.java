package com.prime.optimus.midjourneydigital.excelfile;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
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
