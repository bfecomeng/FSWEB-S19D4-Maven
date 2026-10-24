package com.workintech.s19d1.dto;

import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// POST /movie gövdesi: bir film ve bir aktör
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequest {
    private Movie movie;
    private Actor actor;
}
