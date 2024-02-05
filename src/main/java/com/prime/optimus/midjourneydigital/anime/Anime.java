package com.prime.optimus.midjourneydigital.anime;

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
public class Anime {

    private int naruto = 0;

    @Column(name = "demon_slayer")
    private int demonSlayer = 0;

    @Column(name = "attack_on_titan")
    private int attackOnTitan = 0;

    @Column(name = "my_hero_academia")
    private int myHeroAcademia = 0;

    @Column(name = "one_piece")
    private int onePiece = 0;

    @Column(name = "dragon_ball")
    private int dragonBall = 0;

    @Column(name = "sailor_moon")
    private int sailorMoon = 0;

    @Column(name = "studio_ghibli")
    private int studioGhibli = 0;
}
