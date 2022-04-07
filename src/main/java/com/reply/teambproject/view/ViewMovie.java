package com.reply.teambproject.view;

import com.reply.teambproject.validators.CategoryValidation;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
public class ViewMovie {
    private Long id;
    private String name;
    private String description;
    private Integer category;
}
