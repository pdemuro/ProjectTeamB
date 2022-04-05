package com.reply.teambproject.repository;

import com.reply.teambproject.model.Actor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ActorRepository implements PanacheRepository<Actor> {

    public Actor findByFirstNameAndLastName(String firstName, String lastName){
        return find("firstName = ?1 and lastName = ?2", firstName, lastName).firstResult();
    }

    public boolean existsByFirstNameAndLastName(String firstName, String lastName){
        Actor actor = find("firstName = ?1 and lastName = ?2", firstName, lastName).firstResult();
        return actor != null;
    }

}
