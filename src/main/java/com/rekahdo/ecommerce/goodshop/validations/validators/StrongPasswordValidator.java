package com.rekahdo.ecommerce.goodshop.validations.validators;

import com.rekahdo.ecommerce.goodshop.validations.annotations.StrongPassword;
import com.rekahdo.ecommerce.goodshop.validations.services.StrongPasswordValidationService;

public class StrongPasswordValidator extends Api_Validator<StrongPassword, String> {

    public StrongPasswordValidator() {
        super(new StrongPasswordValidationService());
    }

}
