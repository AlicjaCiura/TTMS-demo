package com.example.demo.validations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class ValidationMaxSize {

    public static final int MAX_SIZE = 5;

    @Constraint(validatedBy = MaxSizeConstraintValidator.class)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface MaxSizeConstraint {
        String message() default "The input list cannot contain more than 5 addresses.";
        Class<?>[] groups() default {};
        Class<? extends String>[] payload() default {};
    }

    public static class MaxSizeConstraintValidator implements ConstraintValidator<MaxSizeConstraint, List<String>> {
        @Override
        public boolean isValid(List<String> values, ConstraintValidatorContext context) {
            return values.size() <= MAX_SIZE;
        }
    }
}
