package com.reply.teambproject.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Objects;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    EMPTY(10001),
    OUT_OF_RANGE(10002),
    MOVIE_ALREADY_EXISTS(10003),
    ACTOR_ALREADY_EXISTS(10004),
    UNKNOWN_ERROR(10005);

    private final Integer code;
    /*public static Integer mapErrors(FieldError fieldError) {

        String error = fieldError.getCode();

        switch (Objects.requireNonNull(error)) {

            case "NotEmpty":
            case "NotNull":{

                return ErrorCode.EMPTY.getCode();

            }

            case "genderValidation": {

                return ErrorCode.OUT_OF_RANGE.getCode();

            }

            default: {

                return ErrorCode.UNKNOWN_ERROR.getCode();

            }

        }
    }*/
}
