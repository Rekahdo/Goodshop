package com.rekahdo.goodshop.user_service.validations.validators;

import com.rekahdo.goodshop.user_service.validations.annotations.ModifiableId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ModifiableIdValidator implements ConstraintValidator<ModifiableId, Long> {

    @Override
    public boolean isValid(Long userId, ConstraintValidatorContext context) {
        if(userId != null && userId == 1L)
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        return true;
    }

}
