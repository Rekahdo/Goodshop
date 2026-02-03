package com.rekahdo.goodshop.admin_service.validations.validators;

import com.rekahdo.goodshop.admin_service.validations.annotations.AcceptableId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AcceptableIdValidator implements ConstraintValidator<AcceptableId, Long> {

    @Override
    public boolean isValid(Long userId, ConstraintValidatorContext context) {
        if(userId != null && userId == 1L)
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        return true;
    }

}
