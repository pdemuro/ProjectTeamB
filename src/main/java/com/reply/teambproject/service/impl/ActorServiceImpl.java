package com.reply.teambproject.service.impl;

import com.reply.teambproject.enums.ErrorCode;
import com.reply.teambproject.exception.AlreadyInDbException;
import com.reply.teambproject.model.Actor;
import com.reply.teambproject.repository.ActorRepository;
import com.reply.teambproject.service.ActorService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ActorServiceImpl implements ActorService {

    @Inject
    private ActorRepository actorRepository;

    @Override
    @Transactional
    public Long addActor(Actor actor) {
        actorRepository.persist(actor);
        actorRepository.flush();
        return actor.getId();
    }

    @Override
    public List<Actor> getActors() {
        return actorRepository.listAll();
    }

    @Override
    public Optional<Actor> findById(Long id) {
        return actorRepository.findByIdOptional(id);
    }

    @Override
    public void update(Long actorId, Actor actor) {

        actorRepository.findByIdOptional(actorId).map(act -> {
            act.setFirstName(actor.getFirstName());
            act.setLastName(actor.getLastName());
            act.setGender(actor.getGender());
            actorRepository.persist(act);
            return act;
        });

    }

    @Override
    public void delete(Long actorId) {
        actorRepository.deleteById(actorId);
    }


}
