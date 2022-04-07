package com.reply.teambproject.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Objects;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    VALIDATION(10001),
    UNKNOWN_ERROR(10002);

    private final Integer code;
}
