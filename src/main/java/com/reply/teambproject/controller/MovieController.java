package com.reply.teambproject.controller;

import com.reply.teambproject.dto.ActorDTO;
import com.reply.teambproject.dto.MovieDTO;
import com.reply.teambproject.service.ActorService;
import com.reply.teambproject.service.MovieService;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;


@RequiredArgsConstructor
@Path("/movies")
public class MovieController {

    @Inject
    ActorService actorService;
    @Inject
    MovieService movieService;

    @Transactional
    @Path("{movieId}/actors")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addActor(@PathParam("movieId") Long movieId, @Valid ActorDTO actorDto, @Context UriInfo uriInfo) {
        movieService.getMovie(movieId);
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(Integer.toString(Math.toIntExact(actorService.addActor(actorDto, movieId))));
        return Response.created(uriBuilder.build()).build();
    }
    @Path("{movieId}/actors")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllActors(@PathParam("movieId") Long movieId ) {
        movieService.getMovie(movieId);
        return Response.ok().entity(actorService.getActorsByMovieId(movieId)).build();
    }

    @Path("{movieId}/actors/{actorID}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getActor(@PathParam("movieId") Long movieId, @PathParam("actorId") Long actorId) {
        movieService.getMovie(movieId);
        return Response.ok( actorService.getActor(actorId)).build();

    }

    @Transactional
    @Path("/{movieId}/actors/{actorID}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modifyActor(@PathParam("movieId") Long movieId, @PathParam("actorId") Long actorId, @Valid ActorDTO actorDto) {

        movieService.getMovie(movieId);
        return Response.ok(actorService.update(actorId, actorDto)).build();
    }

    @Transactional
    @Path("/{movieId}/actors/{actorID}")
    @DELETE
    public Response deleteActor(@PathParam("movieId") Long movieId, @PathParam("actorId") Long actorId) {

        movieService.getMovie(movieId);
        actorService.delete(actorId);
        return Response.ok().build();
    }

    @Transactional
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMovie(@Valid MovieDTO movieDto, @Context UriInfo uriInfo) {

        Long movieId = movieService.addMovie(movieDto);

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(Integer.toString(Math.toIntExact(movieId)));
        return Response.created(uriBuilder.build()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMovies( @DefaultValue("1") @QueryParam("limit") Integer limit,@DefaultValue("20") @QueryParam("offset") Integer offset) {
        return Response.ok().entity( movieService.getMovies(limit, offset)).build();
    }


    @Path("/{movieId}")
    @GET
    public Response getMovie(@PathParam("movieId") Long movieId) {
        return Response.ok( movieService.getMovie(movieId)).build();
    }

    @Transactional
    @Path("/{movieId}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response modifyMovie(@PathParam("movieId") Long movieId, @Valid MovieDTO movieDto) {
        return Response.ok(movieService.update(movieId, movieDto)).build();
    }

    @Transactional
    @Path("/{movieId}")
    @DELETE
    public Response deleteMovie(@PathParam("movieId") Long movieId) {

        movieService.delete(movieId);
        return Response.ok().build();
    }
}

