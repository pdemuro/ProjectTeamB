package com.reply.teambproject.repository;

import com.reply.teambproject.model.Actor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ActorRepository implements PanacheRepository<Actor> {
    public List<Actor> findActorByMovieId(Long movieId) {
        return find("movie.id", movieId).list();
    }

}
