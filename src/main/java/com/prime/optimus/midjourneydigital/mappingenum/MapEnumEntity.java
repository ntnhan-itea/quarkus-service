package com.prime.optimus.midjourneydigital.mappingenum;

import com.prime.optimus.midjourneydigital.audit.Audit;
import com.prime.optimus.midjourneydigital.audit.AuditListener;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@EntityListeners(value = AuditListener.class)
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@Table(name = "map_enum")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class MapEnumEntity extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = StatusConverter.class)
    private StatusEnum statusEnum;
}
