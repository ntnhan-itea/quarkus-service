package com.nhan.nguyenthanh.johnnyservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class Person {

    private String name;

    private int age;

    @NotBlank(message = "City code should not be missing")
    private String cityCode;

    private Student student;
}
