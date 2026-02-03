package com.rekahdo.goodshop.authentication_service.validations.validators;

import com.rekahdo.goodshop.authentication_service.utilities.ApiKey;
import com.rekahdo.goodshop.authentication_service.validations.annotations.ValidApiKey;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class ValidApiKeyValidator implements ConstraintValidator<ValidApiKey, String> {

    @Override
    public boolean isValid(String apikey, ConstraintValidatorContext context) {
        if(apikey == null || !Objects.equals(ApiKey.SERVICE_KEY, apikey))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

        return true;
    }

}
