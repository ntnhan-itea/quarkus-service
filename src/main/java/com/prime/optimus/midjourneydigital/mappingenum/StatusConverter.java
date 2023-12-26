package com.prime.optimus.midjourneydigital.mappingenum;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.Objects;

//@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<StatusEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(StatusEnum status) {
        if (status == null) {
            return null;
        }
        return status.getValue();
    }

    @Override
    public StatusEnum convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return Arrays.stream(StatusEnum.values())
                .filter(e -> Objects.equals(e.getValue(), dbData))
                .findFirst()
                .orElse(null);
    }
}
