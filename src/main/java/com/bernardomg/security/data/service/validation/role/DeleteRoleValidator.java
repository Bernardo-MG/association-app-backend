
package com.bernardomg.security.data.service.validation.role;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.domain.Example;

import com.bernardomg.security.data.persistence.model.PersistentUserRoles;
import com.bernardomg.security.data.persistence.repository.RoleRepository;
import com.bernardomg.security.data.persistence.repository.UserRolesRepository;
import com.bernardomg.validation.Validator;
import com.bernardomg.validation.failure.FieldFailure;
import com.bernardomg.validation.failure.exception.FieldFailureException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class DeleteRoleValidator implements Validator<Long> {

    private final RoleRepository      roleRepository;

    private final UserRolesRepository userRolesRepository;

    public DeleteRoleValidator(final RoleRepository roleRepo, final UserRolesRepository userRolesRepo) {
        super();

        roleRepository = roleRepo;
        userRolesRepository = userRolesRepo;
    }

    @Override
    public final void validate(final Long id) {
        final Collection<FieldFailure> failures;
        FieldFailure                   failure;
        final PersistentUserRoles      sample;

        failures = new ArrayList<>();

        // The role exists
        if (!roleRepository.existsById(id)) {
            log.error("Found no role with id {}", id);
            failure = FieldFailure.of("id", "notExisting", id);
            failures.add(failure);
        }

        sample = new PersistentUserRoles();
        sample.setRoleId(id);

        // No user has the role
        if (userRolesRepository.exists(Example.of(sample))) {
            log.error("Role with id {} has a relationship with a user", id);
            failure = FieldFailure.of("user", "existing", id);
            failures.add(failure);
        }

        if (!failures.isEmpty()) {
            log.debug("Got failures: {}", failures);
            throw new FieldFailureException(failures);
        }
    }

}
