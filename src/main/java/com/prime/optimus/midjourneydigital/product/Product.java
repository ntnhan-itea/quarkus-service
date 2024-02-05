package com.prime.optimus.midjourneydigital.product;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Product {

    @Column(name = "t_shirt")
    private int tShirt = 0;

    @Column(name = "figure_model")
    private int figureModel = 0;

    @Column(name = "sticker")
    private int sticker = 0;

    @Column(name = "key_chain")
    private int keyChain = 0;
}
