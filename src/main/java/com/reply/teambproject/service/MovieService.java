package com.reply.teambproject.service;

import com.reply.teambproject.model.Actor;
import com.reply.teambproject.model.Movie;


import java.util.List;
import java.util.Optional;
public interface MovieService {

    Long addMovie(Movie movie);
    List<Movie> getMovies();
    Optional<Movie> findById(Long id);

    void update(Long movieId, Movie movie) ;
    void delete(Long movieId);
}
