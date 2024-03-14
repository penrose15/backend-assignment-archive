package com.codestates.coffee;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OneBlankValidator implements ConstraintValidator<OneBlank, String> {
    @Override
    public void initialize(OneBlank constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.matches("\\s?[a-zA-Z](\\s?[a-zA-Z])*\\s?$");
    }
}
