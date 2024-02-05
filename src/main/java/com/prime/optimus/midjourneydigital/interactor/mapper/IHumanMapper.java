package com.prime.optimus.midjourneydigital.interactor.mapper;

import com.prime.optimus.midjourneydigital.model.CsvPerson;
import com.prime.optimus.midjourneydigital.model.Person;
import com.prime.optimus.midjourneydigital.model.PersonDto;
import com.prime.optimus.midjourneydigital.model.Student;
import com.prime.optimus.midjourneydigital.model.StudentDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Builder;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.TargetType;

import java.time.LocalDate;

import static org.mapstruct.MappingConstants.ComponentModel.JAKARTA;

@Mapper(
        componentModel = JAKARTA,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        imports = {LocalDate.class},
        builder = @Builder(disableBuilder = true)
)
public interface IHumanMapper {

    @BeanMapping(qualifiedByName = "convertToPerson")
    @Mapping(target = "name", qualifiedByName = "addPrefixName")
    Person convertToPerson(CsvPerson csvPerson);

    @Named("convertToPerson")
    @BeforeMapping
    default void validateBrokerDto(CsvPerson csvPerson, @TargetType Class<?> targetType) {
        if (csvPerson == null) {
            throw new IllegalArgumentException("Update broker data transfer object is null");
        }
    }

    @Named("convertToPerson")
    @AfterMapping
    default void setCityCode(@MappingTarget Person person, CsvPerson csvPerson) {
        person.setCityCode(csvPerson.getCityCode());
    }

    @Named("addPrefixName")
    default String addPrefixName(String name) {
        return "ntnhan-" + name;
    }

    Person convertToPerson(PersonDto personDto);

    Student convert2Student(StudentDto studentDto);


}
