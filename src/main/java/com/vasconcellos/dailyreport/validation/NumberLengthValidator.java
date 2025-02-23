package com.vasconcellos.dailyreport.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NumberLengthValidator implements ConstraintValidator<NumberLength, Long> {

    private int length;

    @Override
    public void initialize(NumberLength annotation) {
        this.length = annotation.length();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null)
            return true;

        boolean isValid = String.valueOf(value).length() == length;

        if (!isValid)
            context.buildConstraintViolationWithTemplate("must have " + length + " digits")
                    .addConstraintViolation();

        return isValid;
    }
}
