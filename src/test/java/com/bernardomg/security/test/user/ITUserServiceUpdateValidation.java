
package com.bernardomg.security.test.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import com.bernardomg.association.test.config.annotation.IntegrationTest;
import com.bernardomg.security.model.DtoUser;
import com.bernardomg.security.model.User;
import com.bernardomg.security.service.UserService;
import com.bernardomg.validation.exception.ValidationException;

@IntegrationTest
@DisplayName("User service - add roles validation")
@Sql({ "/db/queries/security/privilege/multiple.sql", "/db/queries/security/role/single.sql",
        "/db/queries/security/user/single.sql", "/db/queries/security/relationship/role_privilege.sql" })
public class ITUserServiceUpdateValidation {

    @Autowired
    private UserService service;

    public ITUserServiceUpdateValidation() {
        super();
    }

    @Test
    @DisplayName("Throws an exception when the user doesn't exist")
    public void testUpdate_NotExistingUser() {
        final Executable executable;
        final Exception  exception;
        final User       data;

        data = getUser();

        executable = () -> service.update(1L, data);

        exception = Assertions.assertThrows(ValidationException.class, executable);

        Assertions.assertEquals("error.id.notExisting", exception.getMessage());
    }

    private final User getUser() {
        final DtoUser user;

        user = new DtoUser();
        user.setUsername("New name");
        user.setEmail("email");
        user.setCredentialsExpired(false);
        user.setEnabled(true);
        user.setExpired(false);
        user.setLocked(false);

        return user;
    }

}