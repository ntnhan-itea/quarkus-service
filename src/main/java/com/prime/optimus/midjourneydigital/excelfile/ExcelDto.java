package com.prime.optimus.midjourneydigital.excelfile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ExcelDto extends AbsExcelDto {
    private String Id;
    private String name;
}
