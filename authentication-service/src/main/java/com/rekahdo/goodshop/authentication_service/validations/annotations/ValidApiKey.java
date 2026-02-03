package com.rekahdo.goodshop.authentication_service.validations.annotations;

import com.rekahdo.goodshop.authentication_service.validations.validators.ValidApiKeyValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidApiKeyValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidApiKey {

    String message() default "You are not authorized";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}