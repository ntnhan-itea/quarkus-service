package com.nhan.nguyenthanh.johnnyservice.model;


import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CsvPerson extends CsvBean {

    @CsvBindByName(column = "name")
    private String name;

    @CsvBindByName(column = "age")
    private int age;

    @CsvBindByName(column = "City code")
    private String cityCode;
}
