package com.prime.optimus.midjourneydigital.mappingenum;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MapEnumEntityRepository implements PanacheRepositoryBase<MapEnumEntity, Long> {
}