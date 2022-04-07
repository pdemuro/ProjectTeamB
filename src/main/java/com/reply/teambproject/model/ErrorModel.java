package com.reply.teambproject.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorModel {
    private final String fieldName;
    private final String reason;
    private final Integer code;
}
