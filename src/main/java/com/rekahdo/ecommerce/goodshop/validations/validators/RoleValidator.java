package com.rekahdo.ecommerce.goodshop.validations.validators;

import com.rekahdo.ecommerce.goodshop.validations.annotations.Role;
import com.rekahdo.ecommerce.goodshop.validations.services.RoleValidationService;

public class RoleValidator extends Api_Validator<Role, String> {

    public RoleValidator() {
        super(new RoleValidationService());
    }

}