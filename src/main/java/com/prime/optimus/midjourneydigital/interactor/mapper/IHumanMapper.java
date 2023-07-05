package com.prime.optimus.midjourneydigital.interactor.mapper;

import com.prime.optimus.midjourneydigital.model.CsvPerson;
import com.prime.optimus.midjourneydigital.model.Student;
import com.prime.optimus.midjourneydigital.model.Person;
import com.prime.optimus.midjourneydigital.model.PersonDto;
import com.prime.optimus.midjourneydigital.model.StudentDto;
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
