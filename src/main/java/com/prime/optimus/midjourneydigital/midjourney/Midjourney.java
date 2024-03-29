package com.prime.optimus.midjourneydigital.midjourney;

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
public class Midjourney {

    @Column(name = "account")
    private int account = 0;

    @Column(name = "prompt_detail")
    private int promptDetail = 0;

    @Column(name = "tutorial")
    private int tutorial = 0;
}
