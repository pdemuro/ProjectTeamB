package com.reply.teambproject.service;

import com.reply.teambproject.dto.MovieDTO;
import com.reply.teambproject.model.Actor;
import com.reply.teambproject.model.Movie;


import java.util.List;
import java.util.Optional;
public interface MovieService {

    Long addMovie(MovieDTO movie);
    List<MovieDTO> getMovies();
    MovieDTO getMovie(Long movieId);

    MovieDTO update(Long movieId, MovieDTO movie) ;
    void delete(Long movieId);
    void save(Movie movie);
}
