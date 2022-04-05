package com.reply.teambproject.mapper;

import com.reply.teambproject.dto.ActorDTO;
import com.reply.teambproject.dto.MovieDTO;
import com.reply.teambproject.enums.Category;
import com.reply.teambproject.enums.Gender;
import com.reply.teambproject.model.Actor;
import com.reply.teambproject.model.Movie;
import org.mapstruct.Mapper;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@Mapper(componentModel = "cdi")
public interface ActorMappers {

    ActorDTO map(Actor actor);

    Actor map(ActorDTO actor);

    default Integer map(Gender gender) {
        return gender.getCode();
    }

    default Gender map(Integer code) {
        return Gender.getGenderEnum(code);
    }
}
