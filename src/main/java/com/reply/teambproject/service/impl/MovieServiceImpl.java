package com.reply.teambproject.service.impl;

import com.reply.teambproject.dto.MovieDTO;
import com.reply.teambproject.mapper.MovieMappers;
import com.reply.teambproject.model.Movie;
import com.reply.teambproject.repository.MovieRepository;
import com.reply.teambproject.service.MovieService;
import com.reply.teambproject.view.ViewMovie;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class MovieServiceImpl implements MovieService {

    @Inject
    private MovieRepository movieRepository;
    @Inject
    private MovieMappers movieMappers;

    @Override
    @Transactional
    public Long addMovie(MovieDTO movieDto) {

        Movie movie = movieMappers.map(movieDto);
        movieRepository.persist(movie);
        movie.persistAndFlush();
        movie.persist();
        if(movie.isPersistent()) {
            Optional<Movie> optionalMov = movieRepository.findByIdOptional(movie.getId());
            movie = optionalMov.orElseThrow(NotFoundException::new);
            return movie.getId();
        } else {
            throw new PersistenceException();
        }

    }

    @Override
    public List<ViewMovie> getMovies() {
        return movieMappers.map(movieRepository.listAll());
    }

    @Override
    public ViewMovie getMovie(Long movieId) {
        Optional<Movie> optionalMovie = Movie.findByIdOptional(movieId);
        Movie movie = optionalMovie.orElseThrow(NotFoundException::new);
        return movieMappers.map(movie);
    }

    @Override
    public void save(Movie movie) {
        movieRepository.persist(movie);

    }
    @Override
    @Transactional
    public ViewMovie update(Long movieId, MovieDTO movieDto) {

        Movie movie  = movieRepository.findById(movieId);
        if(movie == null) {
            throw new WebApplicationException("Movie with id " + movieId + " does not exist.", 404);
        }
        movieMappers.map(movieDto,movie);
        movie =  movieRepository.getEntityManager().merge(movie);
        return movieMappers.map(movie);
    }

    @Override
    @Transactional
    public void delete(Long movieId) {
        boolean isEntityDeleted = movieRepository.deleteById(movieId);
        if(!isEntityDeleted) {
            throw new WebApplicationException("Movie with id of " + movieId + " does not exist.", 404);
        }
    }


}
