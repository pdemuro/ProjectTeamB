package com.reply.teambproject.mapper;

import com.reply.teambproject.dto.ActorDTO;
import com.reply.teambproject.enums.Gender;
import com.reply.teambproject.model.Actor;
import com.reply.teambproject.view.ViewActor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface ActorMappers {

    ViewActor map(Actor actor);

    Actor map(ActorDTO actor);

    default Integer map(Gender gender) {
        return gender.getCode();
    }

    default Gender map(Integer code) {
        return Gender.getGenderEnum(code);
    }

    List<ViewActor> map(List<Actor> listAll);

    void map(ActorDTO actorDto, @MappingTarget Actor actor);
}
