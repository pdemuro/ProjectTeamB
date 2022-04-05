package com.reply.teambproject.repository;

import com.reply.teambproject.model.Actor;
import com.reply.teambproject.model.Movie;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MovieRepository implements PanacheRepository<Movie> {


}
