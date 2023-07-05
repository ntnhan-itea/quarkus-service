package com.prime.optimus.midjourneydigital.counter;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CounterRepository implements ICounterRepository, PanacheRepositoryBase<Counter, String> {
}
