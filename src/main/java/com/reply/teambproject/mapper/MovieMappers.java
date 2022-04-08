package com.reply.teambproject.mapper;

import com.reply.teambproject.dto.MovieDTO;
import com.reply.teambproject.enums.Category;
import com.reply.teambproject.enums.Gender;
import com.reply.teambproject.model.Movie;
import com.reply.teambproject.view.ViewMovie;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "cdi")

public interface MovieMappers {

    ViewMovie map(Movie movie);

    Movie map(MovieDTO movie);

    default Integer mapCategory(Category category) {
        return category.getCode();
    }

    default Category mapCategory(Integer code) {
        return Category.getCategoryEnum(code);
    }

    default Integer mapGender(Gender gender) {
        return gender.getCode();
    }

    default Gender mapGender(Integer code) {
        return Gender.getGenderEnum(code);
    }

    List<ViewMovie> map(List<Movie> listAll);

    void map(MovieDTO movieDto, @MappingTarget Movie movie);

    Movie map(ViewMovie movie);

}
