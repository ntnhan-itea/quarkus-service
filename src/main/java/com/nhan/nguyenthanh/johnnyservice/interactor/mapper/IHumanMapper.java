package com.nhan.nguyenthanh.johnnyservice.interactor.mapper;

import com.nhan.nguyenthanh.johnnyservice.model.CsvPerson;
import com.nhan.nguyenthanh.johnnyservice.model.Student;
import com.nhan.nguyenthanh.johnnyservice.model.Person;
import com.nhan.nguyenthanh.johnnyservice.model.PersonDto;
import com.nhan.nguyenthanh.johnnyservice.model.StudentDto;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.time.LocalDate;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED, imports = {LocalDate.class})
public interface IHumanMapper {


    @Mapping(target = "name", qualifiedByName = "addPrefixName")
    Person convertToPerson(CsvPerson csvPerson);

    @Named("addPrefixName")
    default String addPrefixName(String name) {
        return "ntnhan-" + name;
    }

    Person convertToPerson(PersonDto personDto);

    Student convert2Student(StudentDto studentDto);


}
