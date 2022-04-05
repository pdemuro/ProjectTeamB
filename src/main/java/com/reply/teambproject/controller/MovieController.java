package com.reply.teambproject.controller;

import com.reply.teambproject.dto.ActorDTO;
import com.reply.teambproject.dto.MovieDTO;
import com.reply.teambproject.mapper.ActorMappers;
import com.reply.teambproject.mapper.MovieMappers;
import com.reply.teambproject.model.Actor;
import com.reply.teambproject.model.Movie;
import com.reply.teambproject.service.ActorService;
import com.reply.teambproject.service.MovieService;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;


@RequiredArgsConstructor
@Path("/movies")
public class MovieController {

    @Inject
    ActorService actorService;
    @Inject
    MovieService movieService;
    @Inject
    ActorMappers actorMappers;
    @Inject
    MovieMappers movieMappers;

    @Transactional
    @Path("{movieId}/actors")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addActor(@PathParam("movieId") Long movieId, @Valid ActorDTO actor, @Context UriInfo uriInfo) {

        Long actorId = actorService.addActor(actorMappers.map(actor));

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(Integer.toString(Math.toIntExact(actorId)));
        return Response.created(uriBuilder.build()).build();
    }
    @Path("{movieId}/actors")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllActors(@PathParam("movieId") Long movieId) {
        if (movieService.findById(movieId).isEmpty()) {
            Response.status(Response.Status.NOT_FOUND);
        }
        List<Actor> actors = actorService.getActors();
        return Response.ok().entity(actors).build();
    }

    @Path("{movieId}/actors/{actorID}")
    @GET
    public Response getActor(@PathParam("movieId") Long movieId, @PathParam("actorId") Long actorId) {
        if (movieService.findById(movieId).isEmpty()) {
            Response.status(Response.Status.NOT_FOUND);
        }

        Response.ResponseBuilder conditionalResponse =  actorService.findById(actorId)
                .map(actor -> Response.ok().entity(actor))
                .orElse(Response.status(Response.Status.NOT_FOUND));

        if(conditionalResponse != null)
            return conditionalResponse.build();
        // preconditions are OK
        return Response.ok(conditionalResponse).build();

    }
    
/*
    @Path("/movies/{movieId}/actors/{actorID}")
    @PUT
    public Response modifyActor(@PathParam("movieId") Long movieId, @PathParam("actorId") Long actorId, @Valid ActorDTO actor) {

        if (!movieService.findById(movieId).isPresent()) {
            Response.status(Response.Status.NOT_FOUND);
        }

        actorService.update(actorId, actorMappers.map(actor));
        return Response.ok().build();
    }

    @Path("/movies/{movieId}/actors/{actorID}")
    @DELETE
    public Response deleteActor(@PathParam("movieId") Long movieId, @PathParam("actorId") Long actorId) {

        if (!movieService.findById(movieId).isPresent()) {
            Response.status(Response.Status.NOT_FOUND);
        }
        actorService.delete(actorId);
        return Response.ok().build();
    }*/

    @Transactional
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMovie(@Valid MovieDTO movie, @Context UriInfo uriInfo) {

        Long movieId = movieService.addMovie(movieMappers.map(movie));

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(Integer.toString(Math.toIntExact(movieId)));
        return Response.created(uriBuilder.build()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMovies() {

        List<Movie> movies = movieService.getMovies();
        return Response.ok().entity(movies).build();
    }

    @Path("/{movieId}")
    @GET
    public Response getMovie(@PathParam("movieId") Long movieId) {
        Response.ResponseBuilder conditionalResponse =  movieService.findById(movieId)
                .map(movie -> Response.ok().entity(movie))
                .orElse(Response.status(Response.Status.NOT_FOUND));

        if(conditionalResponse != null)
            return conditionalResponse.build();
        // preconditions are OK
        return Response.ok(conditionalResponse).build();

    }

    /*@Path("/movies/{movieId}")
    @PUT
    public Response modifyActor(@PathParam("movieId") Long movieId, @Valid MovieDTO movie) {

        movieService.update(movieId, movieMappers.map(movie));
        return Response.ok().build();
    }*/
}

