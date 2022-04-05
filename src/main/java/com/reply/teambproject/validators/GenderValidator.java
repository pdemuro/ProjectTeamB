package com.reply.teambproject.validators;

import com.reply.teambproject.enums.Gender;
import lombok.NoArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;


@NoArgsConstructor
public class GenderValidator implements ConstraintValidator<GenderValidation, Integer> {

    @Override
    public boolean isValid(Integer genderToCompare, ConstraintValidatorContext cxt) {

        for (Gender gender : List.of(Gender.values())) {
            if (gender.getCode().equals(genderToCompare)) {
                return true;
            }
        }
        return false;
    }
}
