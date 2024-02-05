package com.prime.optimus.midjourneydigital.counter;

import com.prime.optimus.midjourneydigital.anime.Anime;
import com.prime.optimus.midjourneydigital.midjourney.Midjourney;
import com.prime.optimus.midjourneydigital.product.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "tbl_counter")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Counter {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private String id = UUID.randomUUID().toString();

    @Embedded
    private Anime anime = new Anime();

    @Embedded
    private Product product = new Product();

    @Embedded
    private Midjourney midjourney = new Midjourney();

}
