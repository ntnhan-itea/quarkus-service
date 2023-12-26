package com.prime.optimus.midjourneydigital.audit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Audit {
    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @Column(name = "modification_time")
    private LocalDateTime modificationTime;

    @Column(name = "modification_user")
    private String modificationUser;
}
