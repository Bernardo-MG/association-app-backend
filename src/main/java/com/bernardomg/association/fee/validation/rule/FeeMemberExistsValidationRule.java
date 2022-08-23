
package com.bernardomg.association.fee.validation.rule;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Component;

import com.bernardomg.association.fee.model.FeeForm;
import com.bernardomg.association.member.repository.MemberRepository;
import com.bernardomg.validation.error.ValidationError;
import com.bernardomg.validation.error.ValidationRule;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public final class FeeMemberExistsValidationRule implements ValidationRule<FeeForm> {

    private final MemberRepository repository;

    @Override
    public final Collection<ValidationError> test(final FeeForm period) {
        final Collection<ValidationError> result;
        final ValidationError             error;

        result = new ArrayList<>();
        if (!repository.existsById(period.getMemberId())) {
            error = ValidationError.of("error.member.notExists");
            result.add(error);
        }

        return result;
    }

}