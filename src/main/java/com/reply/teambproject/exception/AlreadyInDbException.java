package com.reply.teambproject.exception;

import lombok.Getter;

@Getter
public class AlreadyInDbException extends BusinessException {

    private final String name;
    private final String message;
    private final Integer code;
    public AlreadyInDbException(String name, Integer code) {
        this.name = name;
        this.message = "ALREADY FOUND IN DB";
        this.code = code;
    }
}
