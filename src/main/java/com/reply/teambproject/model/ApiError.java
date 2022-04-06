package com.reply.teambproject.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.http.HttpStatus;

import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
public class ApiError<T> {

    private String timestamp;
    private Integer status;
    private String error;
    private String message;
    private List<T> errors;

    public ApiError(Integer status, String error, String message, List<T> errors) {
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        this.status = status;
        this.error = error;
        this.message = message;
        this.errors = errors;
    }

    public ApiError(Response.Status status, String message, List<T> errors) {
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        this.status = status.getStatusCode();
        this.error = status.toString();
        this.message = message;
        this.errors = errors;
    }
}
