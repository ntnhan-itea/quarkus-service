package com.prime.optimus.midjourneydigital.mappingenum;

import com.prime.optimus.midjourneydigital.audit.Audit;
import com.prime.optimus.midjourneydigital.audit.AuditListener;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
