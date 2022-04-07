package com.reply.teambproject.validators;

import com.reply.teambproject.enums.Category;
import com.reply.teambproject.enums.Gender;
import lombok.NoArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;


@NoArgsConstructor
public class CategoryValidator implements ConstraintValidator<CategoryValidation, Integer> {

    @Override
    public boolean isValid(Integer categoryToCompare, ConstraintValidatorContext cxt) {

        for (Category category : List.of(Category.values())) {
            if (category.getCode().equals(categoryToCompare)) {
                return true;
            }
        }
        return false;
    }
}
