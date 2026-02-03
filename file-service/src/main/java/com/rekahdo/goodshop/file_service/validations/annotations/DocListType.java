package com.rekahdo.goodshop.file_service.validations.annotations;

import com.rekahdo.goodshop.file_service.validations.validators.DocListValidator;
import com.rekahdo.goodshop.file_service.validations.validators.DocValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DocListValidator.class)
@Documented
public @interface DocListType {

    String[] allowed() default {"image/jpeg", "image/jpg", "image/png", "application/pdf"};

    String message() default "Invalid document type. Allowed types: JPEG, JPG, PNG, PDF";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}