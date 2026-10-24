package com.workintech.s19d1.util;

import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.exceptions.ApiException;
import org.springframework.http.HttpStatus;

/**
 * Validation kuralları controller dışında, tek yerde tutulur.
 */
public class HollywoodValidation {

    private HollywoodValidation() {
    }

    public static void checkId(Long id) {
        if (id == null || id <= 0) {
            throw new ApiException("Id must be greater than 0: " + id, HttpStatus.BAD_REQUEST);
        }
    }

    public static void validateActor(Actor actor) {
        if (actor == null) {
            throw new ApiException("Actor can not be null", HttpStatus.BAD_REQUEST);
        }
        if (isBlank(actor.getFirstName()) || isBlank(actor.getLastName())) {
            throw new ApiException("Actor firstName and lastName can not be empty", HttpStatus.BAD_REQUEST);
        }
    }

    public static void validateMovie(Movie movie) {
        if (movie == null) {
            throw new ApiException("Movie can not be null", HttpStatus.BAD_REQUEST);
        }
        if (isBlank(movie.getName())) {
            throw new ApiException("Movie name can not be empty", HttpStatus.BAD_REQUEST);
        }
        if (movie.getRating() < 0 || movie.getRating() > 10) {
            throw new ApiException("Movie rating must be between 0 and 10", HttpStatus.BAD_REQUEST);
        }
    }

    private static boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
