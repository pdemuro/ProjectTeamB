package com.reply.teambproject.service.impl;

import com.reply.teambproject.dto.ActorDTO;
import com.reply.teambproject.mapper.ActorMappers;
import com.reply.teambproject.mapper.MovieMappers;
import com.reply.teambproject.model.Actor;
import com.reply.teambproject.repository.ActorRepository;
import com.reply.teambproject.service.ActorService;
import com.reply.teambproject.service.MovieService;
import com.reply.teambproject.view.ViewActor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ActorServiceImpl implements ActorService {

    @Inject
    private ActorRepository actorRepository;
    @Inject
    private ActorMappers actorMappers;
    @Inject
    private MovieMappers movieMappers;
    @Inject
    private MovieService movieService;

    @Override
    @Transactional
    public Long addActor(ActorDTO actorDto, Long movieId) {


        Actor actor = actorMappers.map(actorDto);
        actor.setMovie(movieMappers.map(movieService.getMovie(movieId)));
        actorRepository.persist(actor);
        actor.persistAndFlush();
        actor.persist();
        if (actor.isPersistent()) {
            Optional<Actor> optionalAct = findByIdOptional(actor.getId());
            actor = optionalAct.orElseThrow(NotFoundException::new);
            return actor.getId();
        } else {
            throw new PersistenceException();
        }
    }

    @Override
    public List<ViewActor> getActorsByMovieId(Long movieId) {
        return actorMappers.map(actorRepository.findActorByMovieId(movieId));
    }

    @Override
    public ViewActor getActor(Long actorId) {
        Optional<Actor> optionalActor = findByIdOptional(actorId);
        Actor actor = optionalActor.orElseThrow(NotFoundException::new);
        return actorMappers.map(actor);
    }


    @Override
    public Optional<Actor> findByIdOptional(Long id) {
        return actorRepository.findByIdOptional(id);
    }

    @Override
    @Transactional
    public ViewActor update(Long actorId, ActorDTO actorDto) {

        Actor actor = actorRepository.findById(actorId);
        if (actor == null) {
            throw new WebApplicationException("Actor with id " + actorId + " does not exist.", 404);
        }
        actorMappers.map(actorDto, actor);
        actor = actorRepository.getEntityManager().merge(actor);
        return actorMappers.map(actor);
    }

    @Override
    @Transactional
    public void delete(Long actorId) {
        boolean isEntityDeleted = actorRepository.deleteById(actorId);
        if (!isEntityDeleted) {
            throw new WebApplicationException("Actor with id of " + actorId + " does not exist.", 404);
        }
    }


}
