package com.reply.teambproject.service;

import com.reply.teambproject.dto.MovieDTO;
import com.reply.teambproject.model.Actor;
import com.reply.teambproject.model.Movie;
import com.reply.teambproject.view.ViewMovie;


import java.util.List;
import java.util.Optional;
public interface MovieService {

    Long addMovie(MovieDTO movie);
    List<ViewMovie> getMovies();
    ViewMovie getMovie(Long movieId);

    ViewMovie update(Long movieId, MovieDTO movie) ;
    void delete(Long movieId);
    void save(Movie movie);
}
