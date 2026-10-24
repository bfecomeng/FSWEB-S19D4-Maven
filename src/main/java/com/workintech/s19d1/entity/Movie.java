package com.workintech.s19d1.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "actors")
@Entity
@Table(name = "movie", schema = "fsweb")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "director_name")
    private String directorName;

    @Column(name = "rating")
    private int rating;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    // Many-to-many ilişkinin sahibi Movie; ara tablo: movie_actor
    @JsonIgnoreProperties("movies")
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "movie_actor", schema = "fsweb",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private List<Actor> actors = new ArrayList<>();

    public void addActor(Actor actor) {
        if (actors == null) {
            actors = new ArrayList<>();
        }
        if (!actors.contains(actor)) {
            actors.add(actor);
        }
    }
}
