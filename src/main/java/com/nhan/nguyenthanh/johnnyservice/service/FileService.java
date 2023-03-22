package com.nhan.nguyenthanh.johnnyservice.service;

import com.nhan.nguyenthanh.johnnyservice.interactor.mapper.IHumanMapper;
import com.nhan.nguyenthanh.johnnyservice.model.CsvBean;
import com.nhan.nguyenthanh.johnnyservice.model.CsvPerson;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Slf4j
public class FileService {

    public static final String FORM_DATA_FILE_PROPERTY = "file";
    private static final String CSV_EXTENSION = "csv";

    @Inject
    CsvBeanService csvBeanService;

    @Inject
    IHumanMapper humanMapper;

    public void initialLoadFinmaBrokers(MultipartFormDataInput formData) {
        log.info("Start initial load version finma ID ...");
        try (InputStream csvInputStreamData = getCsvData(formData).getBody(InputStream.class, null)) {
            List<CsvPerson> allCsvObjects = this.csvBeanService.getAllCsvObjects(csvInputStreamData, CsvPerson.class);

            humanMapper.convertToPerson(allCsvObjects.get(0));
//            return processInitialLoadFinmaBrokers(allCsvObjects);
        } catch (IOException e) {
            String message = e.getMessage();
            log.error(message);
            throw new IllegalArgumentException(message);
        } finally {
            log.info("Finished initial load");
        }
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
