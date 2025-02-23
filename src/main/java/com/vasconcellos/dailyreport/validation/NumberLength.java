package com.vasconcellos.dailyreport.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NumberLengthValidator.class)
@Documented
public @interface NumberLength {

    int length();

    String message() default "must have {length} digits";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}