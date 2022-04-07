package com.reply.teambproject.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum Category {
    ACTION(0),
    DRAMA(1),
    COMEDIES(2),
    ANIME(3),
    SENTIMENTAL(4);

    private final Integer code;


    public static boolean contains(Integer code) {
        return Stream.of(Category.values()).map(Category::getCode).collect(Collectors.toList()).contains(code);
    }

    public static Category getCategoryEnum(Integer code) {
        return Stream.of(Category.values())
                .filter(data -> Objects.equals(data.getCode(), code))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Error: There is no Category with code=" + code));
    }

    public static Category getCategory(Integer category) {
        return Stream.of(Category.values())
                .filter(data -> data.getCode().equals(category))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Error: There is no Category with category=" + category));
    }
}
