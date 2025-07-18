package com.rekahdo.ecommerce.goodshop.validations.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.rekahdo.ecommerce.goodshop.validations.validators.RoleValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = RoleValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Role {

    String message() default "Valid Role(s) Provider";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}