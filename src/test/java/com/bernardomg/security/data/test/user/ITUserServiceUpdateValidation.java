
package com.bernardomg.security.data.test.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import com.bernardomg.association.test.config.annotation.IntegrationTest;
import com.bernardomg.security.data.model.DtoUser;
import com.bernardomg.security.data.model.User;
import com.bernardomg.security.data.service.UserService;
import com.bernardomg.validation.failure.FieldFailure;
import com.bernardomg.validation.failure.exception.FieldFailureException;

@IntegrationTest
@DisplayName("User service - add roles validation")
public class ITUserServiceUpdateValidation {

    @Autowired
    private UserService service;

    public ITUserServiceUpdateValidation() {
        super();
    }

    @Test
    @DisplayName("Throws an exception when changing the username")
    @Sql({ "/db/queries/security/privilege/multiple.sql", "/db/queries/security/role/single.sql",
            "/db/queries/security/user/single.sql", "/db/queries/security/relationship/role_privilege.sql" })
    public void testUpdate_ChangeUsername() {
        final Executable            executable;
        final FieldFailureException exception;
        final FieldFailure          failure;
        final DtoUser               data;

        data = getUser();
        data.setUsername("abc");

        executable = () -> service.update(data);

        exception = Assertions.assertThrows(FieldFailureException.class, executable);

        Assertions.assertEquals(1, exception.getFailures()
            .size());

        failure = exception.getFailures()
            .iterator()
            .next();

        Assertions.assertEquals("immutable", failure.getCode());
        Assertions.assertEquals("username", failure.getField());
        Assertions.assertEquals("username.immutable", failure.getMessage());
    }

    @Test
    @DisplayName("Throws an exception when the email already exists")
    @Sql({ "/db/queries/security/privilege/multiple.sql", "/db/queries/security/role/single.sql",
            "/db/queries/security/user/single.sql", "/db/queries/security/user/alternative.sql",
            "/db/queries/security/relationship/role_privilege.sql" })
    public void testUpdate_ExistingMail() {
        final Executable            executable;
        final FieldFailureException exception;
        final FieldFailure          failure;
        final DtoUser               data;

        data = getUser();
        data.setEmail("email2@somewhere.com");

        executable = () -> service.update(data);

        exception = Assertions.assertThrows(FieldFailureException.class, executable);

        Assertions.assertEquals(1, exception.getFailures()
            .size());

        failure = exception.getFailures()
            .iterator()
            .next();

        Assertions.assertEquals("existing", failure.getCode());
        Assertions.assertEquals("email", failure.getField());
        Assertions.assertEquals("email.existing", failure.getMessage());
    }

    @Test
    @DisplayName("Throws an exception when the email doesn't match the valid pattern")
    @Sql({ "/db/queries/security/privilege/multiple.sql", "/db/queries/security/role/single.sql",
            "/db/queries/security/user/single.sql", "/db/queries/security/relationship/role_privilege.sql" })
    public void testUpdate_InvalidMail() {
        final Executable            executable;
        final FieldFailureException exception;
        final FieldFailure          failure;
        final DtoUser               data;

        data = getUser();
        data.setEmail("abc");

        executable = () -> service.update(data);

        exception = Assertions.assertThrows(FieldFailureException.class, executable);

        Assertions.assertEquals(1, exception.getFailures()
            .size());

        failure = exception.getFailures()
            .iterator()
            .next();

        Assertions.assertEquals("invalid", failure.getCode());
        Assertions.assertEquals("email", failure.getField());
        Assertions.assertEquals("email.invalid", failure.getMessage());
    }

    @Test
    @DisplayName("Throws an exception when the user doesn't exist")
    public void testUpdate_NotExistingUser() {
        final Executable            executable;
        final FieldFailureException exception;
        final FieldFailure          failure;
        final User                  data;

        data = getUser();

        executable = () -> service.update(data);

        exception = Assertions.assertThrows(FieldFailureException.class, executable);

        Assertions.assertEquals(1, exception.getFailures()
            .size());

        failure = exception.getFailures()
            .iterator()
            .next();

        Assertions.assertEquals("notExisting", failure.getCode());
        Assertions.assertEquals("id", failure.getField());
        Assertions.assertEquals("id.notExisting", failure.getMessage());
    }

    private final DtoUser getUser() {
        final DtoUser user;

        user = new DtoUser();
        user.setId(1L);
        user.setUsername("admin");
        user.setName("Admin");
        user.setEmail("email@somewhere.com");
        user.setCredentialsExpired(false);
        user.setEnabled(true);
        user.setExpired(false);
        user.setLocked(false);

        return user;
    }

}
