package com.prime.optimus.midjourneydigital.excelfile;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import javax.annotation.PostConstruct;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
public abstract class AbsExcelFile {
    public static final String EXCEL_TEMPLATE_PATH = "/templates/excel/GenerateExcelFile_Template.xlsx";
    public static final String SHEET_NAME = "Customize sheet name";
    protected Workbook workbook;

    public abstract <T extends AbsExcelDto> byte[] generateExcelFromTemplate(List<T> dtoList);

    public final Workbook getWorkBook() throws IOException {
        try (InputStream inp = getClass().getResourceAsStream(EXCEL_TEMPLATE_PATH);
             Workbook workbook = WorkbookFactory.create(inp);
             ByteArrayOutputStream bos = new ByteArrayOutputStream();) {

            workbook.setSheetName(0, SHEET_NAME);

            return workbook;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

}
