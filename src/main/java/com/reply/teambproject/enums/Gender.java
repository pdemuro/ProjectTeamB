package com.reply.teambproject.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Getter
@AllArgsConstructor
public enum Gender {
    MALE(0),
    FEMALE(1),
    NON_BINARY(2);

    private final Integer code;

    public static boolean contains(Integer code) {
        return Stream.of(Gender.values()).map(Gender::getCode).collect(Collectors.toList()).contains(code);
    }

    public static Gender getGenderEnum(Integer code) {
        return Stream.of(Gender.values())
                .filter(data -> Objects.equals(data.getCode(), code))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Error: There is no Gender with code=" + code));
    }

    public static Optional<Gender> getGenderName(Integer code) {
        return Stream.of(Gender.values())
                .filter(data -> Objects.equals(data.getCode(), code))
                .findFirst();
    }


}
