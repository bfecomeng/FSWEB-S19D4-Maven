package com.workintech.s19d1.controller;

import com.workintech.s19d1.dto.MovieRequest;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.service.MovieService;
import com.workintech.s19d1.util.HollywoodValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// context-path /workintech olduğu için gerçek adres: /workintech/movie
@Slf4j
@RestController
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<Movie> findAll() {
        return movieService.findAll();
    }

    @GetMapping("/{id}")
    public Movie findById(@PathVariable Long id) {
        HollywoodValidation.checkId(id);
        return movieService.findById(id);
    }

    // Bir film ve bir aktör alır, ilişkileriyle birlikte kaydeder
    @PostMapping
    public Movie save(@RequestBody MovieRequest movieRequest) {
        Movie movie = movieRequest.getMovie();
        HollywoodValidation.validateMovie(movie);

        Actor actor = movieRequest.getActor();
        if (actor != null) {
            HollywoodValidation.validateActor(actor);
            movie.addActor(actor);
            actor.addMovie(movie);
        }
        return movieService.save(movie);
    }

    @PutMapping("/{id}")
    public Movie update(@PathVariable Long id, @RequestBody Movie movie) {
        HollywoodValidation.checkId(id);
        HollywoodValidation.validateMovie(movie);

        Movie existing = movieService.findById(id);
        existing.setName(movie.getName());
        existing.setDirectorName(movie.getDirectorName());
        existing.setRating(movie.getRating());
        existing.setReleaseDate(movie.getReleaseDate());
        return movieService.save(existing);
    }

    @DeleteMapping("/{id}")
    public Movie delete(@PathVariable Long id) {
        HollywoodValidation.checkId(id);
        Movie movie = movieService.findById(id);
        movieService.delete(movie);
        return movie;
    }
}
