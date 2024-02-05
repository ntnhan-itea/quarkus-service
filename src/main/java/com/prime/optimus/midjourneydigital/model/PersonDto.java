package com.prime.optimus.midjourneydigital.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
