package com.reply.teambproject.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CategoryValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CategoryValidation {

    String message() default "MUST BE IN(action, drama, comedies, anime, sentimental)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
