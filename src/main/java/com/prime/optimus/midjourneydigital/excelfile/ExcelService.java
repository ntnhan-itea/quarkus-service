package com.prime.optimus.midjourneydigital.excelfile;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@ApplicationScoped
@Slf4j
public class ExcelService {
    public static final String EXCEL_TEMPLATE_PATH = "/templates/excel/GenerateExcelFile_Template.xlsx";
    public static final String SHEET_NAME = "Customize sheet name";

    public byte[] generateExcelFromTemplate(List<ExcelDto> excelDtoList) {
        try (InputStream inp = getClass().getResourceAsStream(EXCEL_TEMPLATE_PATH);
             Workbook workbook = WorkbookFactory.create(inp);
             ByteArrayOutputStream bos = new ByteArrayOutputStream();) {

            workbook.setSheetName(0, SHEET_NAME);
            Sheet sheet = workbook.getSheet(SHEET_NAME); // or workbook.getSheet("YourSheetName");

            Row row1 = sheet.getRow(0);
            Cell cell1 = row1.getCell(0);
            cell1.setCellStyle(createHeaderStyle(workbook));

            // Start writing to it
            int rowNum = 1; // Start at 1 if assuming row 0 is header, adjust as needed

            for (ExcelDto excelDto : excelDtoList) {
                Row row = sheet.createRow(rowNum);
                row.createCell(0).setCellValue(rowNum);
                row.createCell(1).setCellValue(excelDto.getId());
                row.createCell(2).setCellValue(excelDto.getName());
                //... add cells for other properties

                rowNum++;
            }

            workbook.write(bos);
            return bos.toByteArray();

        } catch (IOException e) {
            log.error("Cannot generate the XML file", e);
        }

        return new byte[0];
    }

    // Create a method to create header style
    private CellStyle createHeaderStyle(Workbook workbook) {
        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);

        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        return headerCellStyle;
    }

    // Create a method for colored cells
    private CellStyle createColorStyle(Workbook workbook, short colorIndex) {
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(colorIndex);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return style;
    }


}
