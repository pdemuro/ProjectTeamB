package com.reply.teambproject.service.impl;

import com.reply.teambproject.dto.ActorDTO;
import com.reply.teambproject.dto.MovieDTO;
import com.reply.teambproject.enums.Category;
import com.reply.teambproject.enums.Gender;
import com.reply.teambproject.mapper.MovieMappers;
import com.reply.teambproject.model.Actor;
import com.reply.teambproject.model.Movie;
import com.reply.teambproject.repository.MovieRepository;
import com.reply.teambproject.service.MovieService;
import com.reply.teambproject.view.ViewActor;
import com.reply.teambproject.view.ViewMovie;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@QuarkusTest
class MovieServiceImplTest {
    private MovieDTO movieDto;
    private Movie movie;
    private ViewMovie viewMovie;
    private List<Movie> listMovies;

    @Inject
    private MovieService movieService;

    @InjectMock
    private MovieRepository mockMovieRepository;

    @BeforeEach
    private void init() {

        movieDto = new MovieDTO();
        movieDto.setName("Movie Title");
        movieDto.setDescription("Movie Description");
        movieDto.setCategory(Category.ACTION.getCode());

        movie = new Movie();
        movie.setId(1L);
        movie.setName("Movie Title");
        movie.setDescription("Movie Description");
        movie.setCategory(Category.ACTION);

        viewMovie = new ViewMovie();
        viewMovie.setId(movie.getId());
        viewMovie.setName(movie.getName());
        viewMovie.setDescription(movie.getDescription());
        viewMovie.setCategory(Category.ACTION.getCode());

    }
    @Test
    void addMovie() {

        Mockito.when( mockMovieRepository.findByIdOptional(movie.getId())).thenReturn(Optional.of(movie));

        assertDoesNotThrow(() -> movieService.addMovie(movieDto));
        Mockito.verify(mockMovieRepository, times(1)).findByIdOptional(movie.getId());


    }

    @Test
    void getMovie() {

        Mockito.when( mockMovieRepository.findByIdOptional(movie.getId())).thenReturn(Optional.of(movie));

        assertDoesNotThrow(() -> movieService.getMovie(movie.getId()));
        Mockito.verify(mockMovieRepository, times(1)).findByIdOptional(movie.getId());
    }

    @Test
    void update() {
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        Mockito.when(mockMovieRepository.getEntityManager()).thenReturn(entityManager);
        Mockito.when( mockMovieRepository.findById(movie.getId())).thenReturn(movie);
        Mockito.when( entityManager.merge(movie)).thenReturn(movie);
        assertDoesNotThrow(() -> movieService.update(movie.getId(), movieDto));
        Mockito.verify(mockMovieRepository, times(1)).findById(movie.getId());
        Mockito.verify(mockMovieRepository, times(1)).getEntityManager();
        Mockito.verify(entityManager, times(1)).merge(movie);
    }

    @Test
    void delete() {
        Mockito.when( mockMovieRepository.deleteById(movie.getId())).thenReturn(true);

        assertDoesNotThrow(() -> movieService.delete(movie.getId()));
        Mockito.verify(mockMovieRepository, times(1)).deleteById(movie.getId());
    }
}