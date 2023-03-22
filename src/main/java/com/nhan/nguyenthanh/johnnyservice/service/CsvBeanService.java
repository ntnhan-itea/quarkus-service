package com.nhan.nguyenthanh.johnnyservice.service;

import com.nhan.nguyenthanh.johnnyservice.model.CsvBean;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.james.mime4j.Charsets;

import javax.enterprise.context.ApplicationScoped;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@ApplicationScoped
public class CsvBeanService {

    public <T extends CsvBean> CsvToBean<T> getDefaultCsvBean(InputStream inputStream, Class<T> classType) {
        return new CsvToBeanBuilder<T>(new BufferedReader(new InputStreamReader(inputStream, Charsets.UTF_8)))
                .withType(classType)
                .withSeparator(',')
                .withIgnoreLeadingWhiteSpace(true)
                .withIgnoreEmptyLine(true)
                .build();
    }

    public <T extends CsvBean> List<T> getAllCsvObjects(InputStream ips, Class<T> clazz) {
        try {
            return this.getDefaultCsvBean(ips, clazz).parse();
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot parse data from SVC file to model");
        }
    }
}
