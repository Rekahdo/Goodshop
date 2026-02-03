package com.rekahdo.goodshop.file_service.validations.annotations;

import com.rekahdo.goodshop.file_service.validations.validators.DocValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DocValidator.class)
@Documented
public @interface DocType {

    String message() default "Invalid document type. Allowed types: JPEG, JPG, PNG, PDF";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}