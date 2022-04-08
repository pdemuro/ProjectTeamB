package com.reply.teambproject.controller;

import com.reply.teambproject.dto.ActorDTO;
import com.reply.teambproject.dto.MovieDTO;
import com.reply.teambproject.enums.Category;
import com.reply.teambproject.enums.Gender;
import com.reply.teambproject.mapper.ActorMappers;
import com.reply.teambproject.mapper.MovieMappers;
import com.reply.teambproject.model.Actor;
import com.reply.teambproject.model.Movie;
import com.reply.teambproject.service.ActorService;
import com.reply.teambproject.service.MovieService;
import com.reply.teambproject.view.ViewActor;
import com.reply.teambproject.view.ViewMovie;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.RestAssured;
import org.apache.http.HttpHeaders;
import org.apache.http.entity.ContentType;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

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
    }

    @Test
    void addActor() {
        addMovie();
        given()
                .body(actorDto)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when()
                .post("/movies/"+movie.getId()+"/actors")
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .header("location", "http://localhost:8081/movies/"+movie.getId()+"/actors/"+actor.getId());
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
        addMovie();
        addActor();
        given()
                .when()
                .get("/movies/"+movie.getId()+"/actors/"+actor.getId())
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
    }

    @Test
    void modifyActor() {
    }

    @Test
    void deleteActor() {
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
                .statusCode(Response.Status.CREATED.getStatusCode())
                .header("location", "http://localhost:8081/movies/1");
    }

    @Test
    void getAllMovies() {
    }

    @Test
    void getMovie() {
    }

    @Test
    void modifyMovie() {
    }

    @Test
    void deleteMovie() {
    }
}