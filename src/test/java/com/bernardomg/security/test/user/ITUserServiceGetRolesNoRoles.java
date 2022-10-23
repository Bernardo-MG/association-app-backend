
package com.bernardomg.security.test.user;

import org.apache.commons.collections4.IterableUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import com.bernardomg.association.test.config.annotation.IntegrationTest;
import com.bernardomg.security.model.Role;
import com.bernardomg.security.service.UserService;

@IntegrationTest
@DisplayName("User service - get roles")
@Sql({ "/db/queries/security/privilege/multiple.sql", "/db/queries/security/role/single.sql",
        "/db/queries/security/user/single.sql", "/db/queries/security/relationship/role_privilege.sql" })
public class ITUserServiceGetRolesNoRoles {

    @Autowired
    private UserService service;

    public ITUserServiceGetRolesNoRoles() {
        super();
    }

    @Test
    @DisplayName("Returns no roles for a user")
    public void testGetRoles() {
        final Iterable<Role> result;

        result = service.getRoles(1L);

        Assertions.assertEquals(0L, IterableUtils.size(result));
    }

}