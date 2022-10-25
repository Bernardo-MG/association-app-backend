
package com.bernardomg.security.validation.role;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.bernardomg.security.model.Role;
import com.bernardomg.security.validation.role.rule.RoleExistsValidationRule;
import com.bernardomg.validation.RuleValidator;
import com.bernardomg.validation.Validator;

@Component
public final class RoleUpdateValidator implements Validator<Role> {

    private final Validator<Role> validator;

    public RoleUpdateValidator(final RoleExistsValidationRule roleExistsValidationRule) {
        super();

        validator = new RuleValidator<>(Arrays.asList(roleExistsValidationRule));
    }

    @Override
    public final void validate(final Role id) {
        validator.validate(id);
    }

}
