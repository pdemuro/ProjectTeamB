package com.reply.teambproject.controller;

import com.reply.teambproject.dto.ActorDTO;
import com.reply.teambproject.dto.MovieDTO;
import com.reply.teambproject.enums.Category;
import com.reply.teambproject.enums.Gender;
import com.reply.teambproject.model.Actor;
import com.reply.teambproject.model.Movie;
import com.reply.teambproject.service.impl.ActorServiceImpl;
import com.reply.teambproject.service.impl.MovieServiceImpl;
import com.reply.teambproject.view.ViewActor;
import com.reply.teambproject.view.ViewMovie;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

import static io.restassured.RestAssured.given;

@QuarkusTest
class MovieControllerTest {

    private MovieDTO movieDto;
    private ActorDTO actorDto;
    private Movie movie;
    private Actor actor;
    private ViewMovie viewMovie;
    private ViewActor viewActor;
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

        viewMovie = new ViewMovie();
        viewMovie.setId(movie.getId());
        viewMovie.setName(movie.getName());
        viewMovie.setDescription(movie.getDescription());
        viewMovie.setCategory(Category.ACTION.getCode());

        MovieServiceImpl mockMovieService = Mockito.mock(MovieServiceImpl.class);
        Mockito.when(mockMovieService.getMovie(movie.getId())).thenReturn(viewMovie);
        QuarkusMock.installMockForType(mockMovieService, MovieServiceImpl.class);

        ActorServiceImpl mockActorService = Mockito.mock(ActorServiceImpl.class);
        Mockito.when(mockActorService.addActor(actorDto, movie.getId())).thenReturn(actor.getId());
        Mockito.when(mockActorService.findByIdOptional(actor.getId())).thenReturn(Optional.of(actor));
        QuarkusMock.installMockForType(mockActorService, ActorServiceImpl.class);

    }

    @Test
    void addActor() {

        given()
                .body(actorDto)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when()
                .post("/movies/"+movie.getId()+"/actors")
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode());
    }

    @Test
    void getAllActors() {

        given()
                .when()
                .get("/movies/"+movie.getId()+"/actors")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

    }

    @Test
    void getActor() {
        given()
                .when()
                .get("/movies/"+movie.getId()+"/actors/"+actor.getId())
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
    }

    @Test
    void modifyActor() {
        given()
                .when()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .put("/movies/"+movie.getId()+"/actors/"+actor.getId())
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
    }

    @Test
    void deleteActor() {
        given()
                .when()
                .delete("/movies/"+movie.getId()+"/actors/"+actor.getId())
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
    }

    @Test
    void addMovie() {

        given()
                .body(movieDto)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when()
                .post("/movies")
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode());
    }

    @Test
    void getAllMovies() {

        given()
                .when()
                .get("/movies/")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
    }

    @Test
    void getMovie() {
        given()
                .when()
                .get("/movies/"+movie.getId())
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
    }

    @Test
    void modifyMovie() {
        given()
                .when()
                .get("/movies/"+movie.getId()+"/actors/"+actor.getId())
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
    }

    @Test
    void deleteMovie() {
        given()
                .when()
                .delete("/movies/"+movie.getId())
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
    }
}