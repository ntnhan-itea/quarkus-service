package com.prime.optimus.midjourneydigital.mappingenum;

import lombok.Getter;

@Getter
public enum StatusEnum {
    ACTIVE(200),
    INACTIVE(100);

    private final Integer value;

    StatusEnum(int value) {
        this.value = value;
    }
}
