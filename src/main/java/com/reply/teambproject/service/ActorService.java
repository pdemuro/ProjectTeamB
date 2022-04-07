package com.reply.teambproject.service;

import com.reply.teambproject.dto.ActorDTO;
import com.reply.teambproject.view.ViewActor;

import java.util.List;

public interface ActorService {

    Long addActor(ActorDTO actorDto, Long movieId);

    List<ViewActor> getActorsByMovieId(Long movieId);

    ViewActor getActor(Long id);

    ViewActor update(Long actorId, ActorDTO actorDto);

    void delete(Long actorId);
}
