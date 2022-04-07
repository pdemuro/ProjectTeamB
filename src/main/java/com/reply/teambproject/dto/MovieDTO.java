package com.reply.teambproject.dto;

import com.reply.teambproject.validators.CategoryValidation;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
public class MovieDTO {

    @NotEmpty(message = "CAN NOT BE EMPTY")
    @Size(min=2, max=40, message="MUST BE BETWEEN 2 AND 40")
    private String name;

    @NotEmpty(message = "CAN NOT BE EMPTY")
    private String description;

    @NotNull(message = "CAN NOT BE NULL")
    @CategoryValidation
    private Integer category;
}
