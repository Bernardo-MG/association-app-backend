
package com.bernardomg.security.data.validation.user.rule;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.bernardomg.mvc.error.model.Failure;
import com.bernardomg.mvc.error.model.FieldFailure;
import com.bernardomg.security.data.model.User;
import com.bernardomg.security.data.persistence.model.PersistentUser;
import com.bernardomg.security.data.persistence.repository.UserRepository;
import com.bernardomg.validation.ValidationRule;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public final class UserUsernameNotChangedValidationRule implements ValidationRule<User> {

    private final UserRepository repository;

    @Override
    public final Optional<Failure> test(final User user) {
        final Failure                  error;
        final Optional<PersistentUser> entity;
        final Optional<Failure>        result;

        // TODO: Avoid reading in each rule
        entity = repository.findById(user.getId());
        if ((entity.isPresent()) && (!entity.get()
            .getUsername()
            .equals(user.getUsername()))) {
            log.error("Tried to change username for {} with id {}", entity.get()
                .getUsername(),
                entity.get()
                    .getId());
            error = FieldFailure.of("error.username.immutable", "roleForm", "id", user.getId());
            result = Optional.of(error);
        } else {
            result = Optional.empty();
        }

        return result;
    }

    @Override
    public final String toString() {
        return this.getClass()
            .getName();
    }

}
