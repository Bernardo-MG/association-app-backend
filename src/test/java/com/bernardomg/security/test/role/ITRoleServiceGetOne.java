
package com.bernardomg.security.test.role;

import java.util.Optional;

import org.apache.commons.collections4.IterableUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import com.bernardomg.association.test.config.annotation.IntegrationTest;
import com.bernardomg.security.model.Role;
import com.bernardomg.security.service.RoleService;

@IntegrationTest
@DisplayName("Role service - get one")
@Sql({ "/db/queries/security/role/single.sql" })
public class ITRoleServiceGetOne {

    @Autowired
    private RoleService service;

    public ITRoleServiceGetOne() {
        super();
    }

    @Test
    @DisplayName("Returns a single entity by id")
    public void testGetOne_Existing() {
        final Optional<? extends Role> result;

        result = service.getOne(1l);

        Assertions.assertTrue(result.isPresent());
    }

    @Test
    @DisplayName("Returns the correct data when reading a single entity")
    public void testGetOne_Existing_Data() {
        final Role result;

        result = service.getOne(1l)
            .get();

        Assertions.assertNotNull(result.getId());
        Assertions.assertEquals("ADMIN", result.getName());
        Assertions.assertEquals(0, IterableUtils.size(result.getPrivileges()));
    }

    @Test
    @DisplayName("When reading a single entity with an invalid id, no entity is returned")
    public void testGetOne_NotExisting() {
        final Optional<? extends Role> result;

        result = service.getOne(-1L);

        Assertions.assertFalse(result.isPresent());
    }

}
