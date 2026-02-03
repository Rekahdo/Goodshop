package com.rekahdo.goodshop.admin_service.validations.annotations;

import com.rekahdo.goodshop.admin_service.validations.validators.AssignableRoleValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AssignableRoleValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface AssignableRole {

    String[] roles() default {"MODERATOR", "EDITOR"};

    String message() default "'role' contains an invalid value";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}