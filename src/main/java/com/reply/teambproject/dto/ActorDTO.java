package com.reply.teambproject.dto;

import com.reply.teambproject.enums.Gender;
import com.reply.teambproject.validators.GenderValidation;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
public class ActorDTO {

    @NotEmpty(message = "CAN NOT BE EMPTY")
    @Size(min=2, max=40, message="MUST BE BETWEEN 2 AND 40")
    private String firstName;

    @NotEmpty(message = "CAN NOT BE EMPTY")
    @Size(min=2, max=40, message="MUST BE BETWEEN 2 AND 40")
     private String lastName;

    @NotNull(message = "CAN NOT BE NULL")
    @GenderValidation
    private Integer gender;
}
