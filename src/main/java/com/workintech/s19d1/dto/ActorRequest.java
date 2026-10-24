package com.workintech.s19d1.dto;

import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// POST /actor gövdesi: bir aktör ve oynadığı filmler
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActorRequest {
    private Actor actor;
    private List<Movie> movies;
}
