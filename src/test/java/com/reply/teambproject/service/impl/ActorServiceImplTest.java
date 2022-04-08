package com.reply.teambproject.service.impl;

import com.reply.teambproject.dto.ActorDTO;
import com.reply.teambproject.dto.MovieDTO;
import com.reply.teambproject.enums.Category;
import com.reply.teambproject.enums.Gender;
import com.reply.teambproject.model.Actor;
import com.reply.teambproject.model.Movie;
import com.reply.teambproject.repository.ActorRepository;
import com.reply.teambproject.repository.MovieRepository;
import com.reply.teambproject.service.ActorService;
import com.reply.teambproject.service.MovieService;
import com.reply.teambproject.view.ViewActor;
import com.reply.teambproject.view.ViewMovie;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

@QuarkusTest
class ActorServiceImplTest {

    private MovieDTO movieDto;
    private ActorDTO actorDto;
    private Movie movie;
    private Actor actor;
    private ViewMovie viewMovie;
    private ViewActor viewActor;
    private List<Actor> actors;

    @Inject
    private ActorService actorService;
    @Inject
    private MovieService movieService;
    @InjectMock
    private ActorRepository mockActorRepository;
    @InjectMock
    private MovieRepository mockMovieRepository;
    @BeforeEach
    private void init() {
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

        actorDto = new ActorDTO();
        actorDto.setFirstName("Gianni");
        actorDto.setLastName("Cerquieti");
        actorDto.setGender(Gender.MALE.getCode());

        actor = new Actor();
        actor.setId(1L);
        actor.setFirstName("Gianni");
        actor.setLastName("Cerquieti");
        actor.setMovie(movie);
        actor.setGender(Gender.MALE);

        viewActor = new ViewActor();
        viewActor.setId(1L);
        viewActor.setFirstName("Gianni");
        viewActor.setLastName("Cerquieti");
        viewActor.setGender(Gender.MALE.getCode());

        actors = new ArrayList<Actor>();

        actors.add(actor);

    }

    @Test
    void addActor() {

        /*Mockito.when( mockActorRepository.findByIdOptional(actor.getId())).thenReturn(Optional.of(actor));
        Mockito.when( mockMovieRepository.findByIdOptional(movie.getId())).thenReturn(Optional.of(movie));

        assertDoesNotThrow(() -> actorService.addActor(actorDto, movie.getId()));
        Mockito.verify(mockActorRepository, times(1)).findByIdOptional(actor.getId());
        Mockito.verify(mockMovieRepository, times(1)).findByIdOptional(movie.getId());*/

    }

    @Test
    void getActorsByMovieId() {

        Mockito.when( mockActorRepository.findActorByMovieId(actor.getId())).thenReturn(actors);

        assertDoesNotThrow(() -> actorService.getActorsByMovieId(1L));
        Mockito.verify(mockActorRepository, times(1)).findActorByMovieId(actor.getId());

    }

    @Test
    void getActor() {
        Mockito.when( mockActorRepository.findByIdOptional(actor.getId())).thenReturn(Optional.of(actor));

        assertDoesNotThrow(() -> actorService.getActor(actor.getId()));
        Mockito.verify(mockActorRepository, times(1)).findByIdOptional(actor.getId());
    }

    @Test
    void update() {
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        Mockito.when(mockActorRepository.getEntityManager()).thenReturn(entityManager);
        Mockito.when( mockActorRepository.findById(actor.getId())).thenReturn(actor);
        Mockito.when( entityManager.merge(actor)).thenReturn(actor);
        assertDoesNotThrow(() -> actorService.update(actor.getId(), actorDto));
        Mockito.verify(mockActorRepository, times(1)).findById(actor.getId());
        Mockito.verify(mockActorRepository, times(1)).getEntityManager();
        Mockito.verify(entityManager, times(1)).merge(actor);
    }

    @Test
    void delete() {
        Mockito.when( mockActorRepository.deleteById(actor.getId())).thenReturn(true);

        assertDoesNotThrow(() -> actorService.delete(actor.getId()));
        Mockito.verify(mockActorRepository, times(1)).deleteById(actor.getId());
    }
}