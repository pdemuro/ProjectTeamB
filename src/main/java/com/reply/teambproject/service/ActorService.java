package com.reply.teambproject.service;

import com.reply.teambproject.dto.ActorDTO;
import com.reply.teambproject.model.Actor;

import java.util.List;
import java.util.Optional;

public interface ActorService {

    Long addActor(ActorDTO actorDto, Long movieId);
    List<ActorDTO> getActors();
    ActorDTO getActor(Long id);

    ActorDTO update(Long actorId, ActorDTO actorDto) ;
    void delete(Long actorId);
}
