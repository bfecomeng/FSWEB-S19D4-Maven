package com.workintech.s19d1.controller;

import com.workintech.s19d1.dto.ActorRequest;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.service.ActorService;
import com.workintech.s19d1.util.HollywoodValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// context-path /workintech olduğu için gerçek adres: /workintech/actor
@Slf4j
@RestController
@RequestMapping("/actor")
public class ActorController {

    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    public List<Actor> findAll() {
        return actorService.findAll();
    }

    @GetMapping("/{id}")
    public Actor findById(@PathVariable Long id) {
        HollywoodValidation.checkId(id);
        return actorService.findById(id);
    }

    // Bir aktör ve filmlerini alır, ilişkileriyle birlikte kaydeder
    @PostMapping
    public Actor save(@RequestBody ActorRequest actorRequest) {
        Actor actor = actorRequest.getActor();
        HollywoodValidation.validateActor(actor);

        List<Movie> movies = actorRequest.getMovies();
        if (movies != null) {
            for (Movie movie : movies) {
                actor.addMovie(movie);
                movie.addActor(actor);
            }
        }
        return actorService.save(actor);
    }

    @PutMapping("/{id}")
    public Actor update(@PathVariable Long id, @RequestBody Actor actor) {
        HollywoodValidation.checkId(id);
        HollywoodValidation.validateActor(actor);

        Actor existing = actorService.findById(id);
        existing.setFirstName(actor.getFirstName());
        existing.setLastName(actor.getLastName());
        existing.setGender(actor.getGender());
        existing.setBirthDate(actor.getBirthDate());
        return actorService.save(existing);
    }

    @DeleteMapping("/{id}")
    public Actor delete(@PathVariable Long id) {
        HollywoodValidation.checkId(id);
        Actor actor = actorService.findById(id);
        actorService.delete(actor);
        return actor;
    }
}
