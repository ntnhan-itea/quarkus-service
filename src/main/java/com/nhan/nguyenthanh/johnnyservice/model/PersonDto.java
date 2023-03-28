package com.nhan.nguyenthanh.johnnyservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonDto {

    private String name;

    private int age;

    @NotBlank(message = "City code should not be missing")
    private String cityCode;

    private StudentDto student;
}
