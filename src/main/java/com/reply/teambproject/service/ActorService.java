package com.reply.teambproject.service;

import com.reply.teambproject.dto.ActorDTO;
import com.reply.teambproject.model.Actor;
import com.reply.teambproject.view.ViewActor;

import java.util.List;
import java.util.Optional;

public interface ActorService {

    Long addActor(ActorDTO actorDto, Long movieId);

    List<ViewActor> getActorsByMovieId(Long movieId);

    ViewActor getActor(Long id);

    Optional<Actor> findByIdOptional(Long id);

    ViewActor update(Long actorId, ActorDTO actorDto);

    void delete(Long actorId);
}
