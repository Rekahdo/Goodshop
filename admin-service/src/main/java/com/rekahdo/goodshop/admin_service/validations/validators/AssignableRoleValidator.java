package com.rekahdo.goodshop.admin_service.validations.validators;

import com.rekahdo.goodshop.admin_service.enums.Role;
import com.rekahdo.goodshop.admin_service.validations.annotations.AssignableRole;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Objects;

@Service
public class AssignableRoleValidator implements ConstraintValidator<AssignableRole, String> {

    public String[] assignableRoles;

    @Override
    public void initialize(AssignableRole constraintAnnotation) {
        // Exclude ADMIN role from the assignable roles
        assignableRoles = Arrays.stream(Role.values()).map(Role::name)
                .filter(name -> !Objects.equals(name, Role.ADMIN.name()))
                .toArray(String[]::new);
    }

    @Override
    public boolean isValid(String role, ConstraintValidatorContext context) {
        if(role != null && !Arrays.asList(assignableRoles).contains(role.toUpperCase()))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    String.format("You are FORBIDDEN to assign this role to any user. Acceptable roles: {%s}",
                            String.join(", ", assignableRoles)));

        return true;
    }

}
