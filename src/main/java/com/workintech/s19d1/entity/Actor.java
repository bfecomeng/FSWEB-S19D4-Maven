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
@ToString(exclude = "movies")
@Entity
@Table(name = "actor", schema = "fsweb")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @JsonIgnoreProperties("actors")
    @ManyToMany(mappedBy = "actors",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<Movie> movies = new ArrayList<>();

    public void addMovie(Movie movie) {
        if (movies == null) {
            movies = new ArrayList<>();
        }
        if (!movies.contains(movie)) {
            movies.add(movie);
        }
    }
}
