package com.reply.teambproject.service;

import com.reply.teambproject.model.Actor;

import java.util.List;
import java.util.Optional;

public interface ActorService {

    Long addActor(Actor actor);
    List<Actor> getActors();
    Optional<Actor> findById(Long id);

    void update(Long actorId, Actor actor) ;
    void delete(Long actorId);
}
