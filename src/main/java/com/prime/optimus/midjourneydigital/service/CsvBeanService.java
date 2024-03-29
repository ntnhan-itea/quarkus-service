package com.prime.optimus.midjourneydigital.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.prime.optimus.midjourneydigital.model.CsvBean;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.james.mime4j.Charsets;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@ApplicationScoped
public class CsvBeanService {

    public static final char SEPARATOR = ',';

//    @Inject
//    Logger LOGGER;

    public <T extends CsvBean> CsvToBean<T> getDefaultCsvBean(InputStream inputStream, Class<T> classType) {
        return new CsvToBeanBuilder<T>(new BufferedReader(new InputStreamReader(inputStream, Charsets.UTF_8)))
                .withType(classType)
                .withSeparator(SEPARATOR)
                .withIgnoreLeadingWhiteSpace(true)
                .withIgnoreEmptyLine(true)
                .build();
    }

    public <T extends CsvBean> List<T> getAllCsvObjects(InputStream ips, Class<T> clazz) {
        try {
            return this.getDefaultCsvBean(ips, clazz).parse();
        } catch (Exception e) {
//            LOGGER.error("Cannot parse data from CSV file to model");
            throw e;
        }
    }
}
