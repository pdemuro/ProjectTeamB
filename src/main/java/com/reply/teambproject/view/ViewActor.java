package com.reply.teambproject.view;

import com.reply.teambproject.validators.GenderValidation;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
public class ViewActor {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer gender;

}
