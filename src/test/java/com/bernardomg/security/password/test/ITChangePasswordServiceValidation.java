
package com.bernardomg.security.password.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import com.bernardomg.association.test.config.annotation.IntegrationTest;
import com.bernardomg.security.exception.InvalidPasswordException;
import com.bernardomg.security.password.service.ChangePasswordService;
import com.bernardomg.validation.exception.ValidationException;

@IntegrationTest
@DisplayName("Change password service - validation")
@Sql({ "/db/queries/security/user/single.sql" })
public class ITChangePasswordServiceValidation {

    @Autowired
    private ChangePasswordService service;

    public ITChangePasswordServiceValidation() {
        super();
    }

    @Test
    @DisplayName("Throws an exception when the new password is empty")
    public void testChangePassword_EmptyPassword() {
        final Executable executable;
        final Exception  exception;

        executable = () -> service.changePassword("admin", "1234", "");

        exception = Assertions.assertThrows(ValidationException.class, executable);

        Assertions.assertEquals("error.password.invalid", exception.getMessage());
    }

    @Test
    @DisplayName("Throws an exception when the username doesn't exists")
    public void testChangePassword_NotExistingUsername() {
        final Executable executable;
        final Exception  exception;

        executable = () -> service.changePassword("abc", "1234", "1");

        exception = Assertions.assertThrows(ValidationException.class, executable);

        Assertions.assertEquals("error.username.notExisting", exception.getMessage());
    }

    @Test
    @DisplayName("Throws an exception when the password doesn't match the user's")
    public void testChangePassword_PasswordNotMatches() {
        final Executable executable;
        final Exception  exception;

        executable = () -> service.changePassword("admin", "1", "12");

        exception = Assertions.assertThrows(InvalidPasswordException.class, executable);

        Assertions.assertEquals("Incorrect password", exception.getMessage());
    }

}
