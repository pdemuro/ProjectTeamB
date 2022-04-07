package com.reply.teambproject.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = GenderValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GenderValidation {

    String message() default "MUST BE IN(female, male, non binary)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
