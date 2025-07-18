package com.rekahdo.ecommerce.goodshop.validations.validators;

import com.rekahdo.ecommerce.goodshop.validations.annotations.Username;
import com.rekahdo.ecommerce.goodshop.validations.services.UsernameValidationService;

public class UsernameValidator extends Api_Validator<Username, String> {

    public UsernameValidator() {
        super(new UsernameValidationService());
    }

}
