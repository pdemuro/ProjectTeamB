package com.reply.teambproject.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.reply.teambproject.enums.ErrorCode;
import com.reply.teambproject.model.ApiError;
import com.reply.teambproject.model.ErrorModel;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.List;
import java.util.stream.Collectors;

@Provider
public class ValidationExceptionHandler implements ExceptionMapper<ConstraintViolationException>
{

  @Override
  public Response toResponse(ConstraintViolationException ex) {
    List<ErrorModel> errors = ex.getConstraintViolations().stream()
            .map(error -> new ErrorModel(error.getPropertyPath().toString(), error.getMessage(), ErrorCode.VALIDATION.getCode()))
            .collect(Collectors.toList());
    return Response.status(Status.BAD_REQUEST).entity(new ApiError<>(Status.BAD_REQUEST, "Input data validation failure", errors)).build();
  }
}