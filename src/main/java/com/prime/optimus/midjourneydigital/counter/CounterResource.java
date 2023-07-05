package com.prime.optimus.midjourneydigital.counter;

import com.prime.optimus.midjourneydigital.anime.Anime;
import com.prime.optimus.midjourneydigital.midjourney.Midjourney;
import com.prime.optimus.midjourneydigital.product.Product;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@ApplicationScoped
@Path("api/counter")
public class CounterResource {

    private final CounterRepository counterRepository;

    public CounterResource(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    @PUT
    @Path("{id}/increment")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Counter incrementCounter(@PathParam(value = "id") String id, @RequestBody Counter counter) {
        Counter counterExisting = this.counterRepository.findById(id);

        if (counterExisting == null) {
            throw new IllegalArgumentException("Cannot found with id " + id);
        }

        this.updateAnimeCounter(counterExisting, counter);
        this.updateProductCounter(counterExisting, counter);
        this.updateMidjourneyCounter(counterExisting, counter);

        return counterExisting;
    }

    private void updateAnimeCounter(Counter counterExisting, Counter counter) {
        Anime anime = counter.getAnime();
        Anime animeExisting = counterExisting.getAnime();

        if (animeExisting == null || anime == null) {
            return;
        }

        animeExisting.setNaruto(animeExisting.getNaruto() + anime.getNaruto());

        animeExisting.setDemonSlayer(animeExisting.getDemonSlayer() + anime.getDemonSlayer());

        animeExisting.setAttackOnTitan(animeExisting.getAttackOnTitan() + anime.getAttackOnTitan());

        animeExisting.setMyHeroAcademia(animeExisting.getMyHeroAcademia() + anime.getMyHeroAcademia());

        animeExisting.setOnePiece(animeExisting.getOnePiece() + anime.getOnePiece());

        animeExisting.setDragonBall(animeExisting.getDragonBall() + anime.getDragonBall());

        animeExisting.setSailorMoon(animeExisting.getSailorMoon() + anime.getSailorMoon());

        animeExisting.setStudioGhibli(animeExisting.getStudioGhibli() + anime.getStudioGhibli());
    }

    private void updateProductCounter(Counter counterExisting, Counter counter) {
        Product product = counter.getProduct();
        Product productExisting = counterExisting.getProduct();

        if (productExisting == null || product == null) {
            return;
        }

        productExisting.setFigureModel(productExisting.getFigureModel() + product.getFigureModel());
        productExisting.setTShirt(productExisting.getTShirt() + product.getTShirt());
        productExisting.setSticker(productExisting.getSticker() + product.getSticker());
        productExisting.setKeyChain(productExisting.getKeyChain() + product.getKeyChain());
    }

    private void updateMidjourneyCounter(Counter counterExisting, Counter counter) {
        Midjourney midjourney = counter.getMidjourney();
        Midjourney midjourneyExisting = counterExisting.getMidjourney();

        if (midjourney == null || midjourneyExisting == null) {
            return;
        }

        midjourneyExisting.setAccount(midjourneyExisting.getAccount() + midjourney.getAccount());
        midjourneyExisting.setTutorial(midjourneyExisting.getTutorial() + midjourney.getTutorial());
        midjourneyExisting.setPromptDetail(midjourneyExisting.getPromptDetail() + midjourney.getPromptDetail());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("read")
    public Counter getCount() {
        Optional<Counter> counter = this.counterRepository.findAll().stream().findFirst();

        if (counter.isEmpty()) {
            Counter counter1 = new Counter();
            this.counterRepository.persist(counter1);
            return counter1;
        }

        return counter.get();
    }

}
