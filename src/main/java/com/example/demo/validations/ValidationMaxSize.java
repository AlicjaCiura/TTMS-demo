package com.example.demo.validations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class ValidationMaxSize {

    @Constraint(validatedBy = MaxSizeConstraintValidator.class)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface MaxSizeConstraint {
        String message() default "The input list cannot contain more than 5 addresses.";
    }

    public static class MaxSizeConstraintValidator implements ConstraintValidator<MaxSizeConstraint, List<String>> {
        @Override
        public boolean isValid(List<String> values, ConstraintValidatorContext context) {
            return values.size() <= 5;
        }
    }
}
