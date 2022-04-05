package com.reply.teambproject.service.impl;

import com.reply.teambproject.enums.ErrorCode;
import com.reply.teambproject.exception.AlreadyInDbException;
import com.reply.teambproject.model.Actor;
import com.reply.teambproject.model.Movie;
import com.reply.teambproject.repository.ActorRepository;
import com.reply.teambproject.repository.MovieRepository;
import com.reply.teambproject.service.ActorService;
import com.reply.teambproject.service.MovieService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class MovieServiceImpl implements MovieService {

    @Inject
    private MovieRepository movieRepository;

    @Override
    @Transactional
    public Long addMovie(Movie movie) {

        movieRepository.persist(movie);
        movieRepository.flush();
        return movie.getId();

    }

    @Override
    public List<Movie> getMovies() {
        return movieRepository.listAll();
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return movieRepository.findByIdOptional(id);
    }

    @Override
    public void update(Long movieId, Movie movie) {
        movieRepository.findByIdOptional(movieId).map(mov -> {
            mov.setName(movie.getName());
            mov.setCategory(movie.getCategory());
            mov.setDescription(movie.getDescription());
            movieRepository.persist(mov);
            return mov;
        });
    }

    @Override
    public void delete(Long movieId) {
        movieRepository.deleteById(movieId);
    }


}
