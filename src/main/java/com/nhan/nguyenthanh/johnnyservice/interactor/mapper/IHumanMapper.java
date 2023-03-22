package com.nhan.nguyenthanh.johnnyservice.interactor.mapper;

import com.nhan.nguyenthanh.johnnyservice.model.CsvPerson;
import com.nhan.nguyenthanh.johnnyservice.model.Person;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;

import java.time.LocalDate;

@Mapper(componentModel = "cdi", collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED, imports = {LocalDate.class})
public interface IHumanMapper {


    Person convertToPerson(CsvPerson csvPerson);
}
