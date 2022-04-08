package com.reply.teambproject.service;

import com.reply.teambproject.dto.MovieDTO;
import com.reply.teambproject.view.ViewMovie;

import java.util.List;
public interface MovieService {

    Long addMovie(MovieDTO movie);
    List<ViewMovie> getMovies(Integer limit, Integer offset);
    ViewMovie getMovie(Long movieId);

    ViewMovie update(Long movieId, MovieDTO movie) ;
    void delete(Long movieId);
}
