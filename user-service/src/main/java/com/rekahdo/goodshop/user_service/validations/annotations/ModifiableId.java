package com.rekahdo.goodshop.user_service.validations.annotations;

import com.rekahdo.goodshop.user_service.validations.validators.ModifiableIdValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ModifiableIdValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ModifiableId {

    String message() default "'id' contains an invalid value";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}