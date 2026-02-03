package com.rekahdo.goodshop.vendor_service.validations.annotations;

import com.rekahdo.goodshop.vendor_service.validations.validators.AcceptableIdValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AcceptableIdValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface AcceptableId {

    String message() default "'userId' contains an invalid value";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}