package com.codestates.coffee;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public class OneSpaceValidator implements ConstraintValidator<OneSpace, String> {
    @Override
    public void initialize(OneSpace constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        try {
            String temp = value.replaceAll("\\s", "");
           return value.length() - temp.length() <= 1 ;
        } catch (NullPointerException e) {
            return true;
        }

    }
}
