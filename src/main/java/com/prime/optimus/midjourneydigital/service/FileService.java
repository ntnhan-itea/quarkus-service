package com.prime.optimus.midjourneydigital.service;

import com.prime.optimus.midjourneydigital.interactor.mapper.IHumanMapper;
import com.prime.optimus.midjourneydigital.model.CsvPerson;
import com.prime.optimus.midjourneydigital.model.Person;
import com.prime.optimus.midjourneydigital.model.PersonDto;
import com.prime.optimus.midjourneydigital.model.StudentDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@Slf4j
public class FileService {

    public static final String FORM_DATA_FILE_PROPERTY = "file";
    private static final String CSV_EXTENSION = "csv";

    @Inject
    CsvBeanService csvBeanService;

    @Inject
    IHumanMapper humanMapper;

    public Map<String, List<Person>> parsingPeople(MultipartFormDataInput formData) {
        log.info("Start initial load version finma ID ...");
        this.TestMethod();

        try (InputStream csvInputStreamData = getCsvData(formData).getBody(InputStream.class, null)) {
            List<CsvPerson> allCsvObjects = this.csvBeanService.getAllCsvObjects(csvInputStreamData, CsvPerson.class);
            return this.importPerson(allCsvObjects);
        } catch (IOException e) {
            String message = e.getMessage();
            log.error(message);
            throw new IllegalArgumentException(message);
        } finally {
            log.info("Finished initial load");
        }
    }

    private void TestMethod() {
        StudentDto studentDto = new StudentDto();
        studentDto.setCodeStudent("B1805797");

        PersonDto personDto = new PersonDto();
        personDto.setAge(18);
        personDto.setCityCode("65");
        personDto.setName("Thanh Cong");
        personDto.setStudent(studentDto);

        Person person = this.humanMapper.convertToPerson(personDto);
        System.out.println(person.toString());
    }

    private Map<String, List<Person>> importPerson(List<CsvPerson> csvPersonList) {
        return Optional.ofNullable(csvPersonList).stream()
                .flatMap(Collection::stream)
                .map(humanMapper::convertToPerson)
                .collect(Collectors.groupingBy(e -> e.getCityCode().trim()));
    }


    private InputPart getCsvData(MultipartFormDataInput input) throws RuntimeException {
        return input.getFormDataMap()
                .get(FORM_DATA_FILE_PROPERTY)
                .stream()
                .filter(this::validateExtension)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Missing required field"));
    }

    private boolean validateExtension(InputPart inputPart) {
        Optional<String> validType = Optional.ofNullable(inputPart.getMediaType())
                .map(MediaType::getSubtype)
                .filter(CSV_EXTENSION::equals);

        if (validType.isPresent()) {
            return true;
        }

        String message = String.format("The file only accept [%s] file.", CSV_EXTENSION);
        throw new IllegalArgumentException(message);
    }

}
