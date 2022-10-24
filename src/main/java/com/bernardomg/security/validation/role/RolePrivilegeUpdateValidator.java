
package com.bernardomg.security.validation.role;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.bernardomg.security.validation.role.rule.PrivilegeIdExistsValidationRule;
import com.bernardomg.validation.RuleValidator;
import com.bernardomg.validation.Validator;

@Component
public final class RolePrivilegeUpdateValidator implements Validator<Long> {

    private final Validator<Long> validator;

    public RolePrivilegeUpdateValidator(final PrivilegeIdExistsValidationRule roleIdExistsValidationRule) {
        super();

        validator = new RuleValidator<>(Arrays.asList(roleIdExistsValidationRule));
    }

    @Override
    public final void validate(final Long period) {
        validator.validate(period);
    }

}