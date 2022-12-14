
package com.bernardomg.security.data.service;

import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.bernardomg.security.data.model.Privilege;
import com.bernardomg.security.data.model.Role;

public interface RoleService {

    /**
     * Adds a privilege to a role.
     *
     * @param id
     *            role id
     * @param privilege
     *            privilege id to add
     * @return {@code true} if it managed to add the privilege, {@code false} otherwise
     */
    public Boolean addPrivilege(final Long id, final Long privilege);

    /**
     * Persists the received user.
     *
     * @param role
     *            role to persist
     * @return the persisted role
     */
    public Role create(final Role role);

    /**
     * Deletes the role with the received id.
     *
     * @param id
     *            id of the role to delete
     * @return {@code true} if it managed to delete, {@code false} otherwise
     */
    public Boolean delete(final Long id);

    /**
     * Returns all the roles matching the sample. If the sample fields are empty, then all the roles are returned.
     *
     * @param sample
     *            sample for filtering
     * @param pageable
     *            pagination to apply
     * @return all the roles matching the sample
     */
    public Iterable<? extends Role> getAll(final Role sample, final Pageable pageable);

    /**
     * Returns the role for the received id, if it exists. Otherwise an empty {@code Optional} is returned.
     *
     * @param id
     *            id of the role to acquire
     * @return an {@code Optional} with the role, if it exists, of an empty {@code Optional} otherwise
     */
    public Optional<? extends Role> getOne(final Long id);

    /**
     * Returns all privileges for a role.
     *
     * @param id
     *            role id
     * @param pageable
     *            pagination to apply
     * @return privileges for the role
     */
    public Iterable<? extends Privilege> getPrivileges(final Long id, final Pageable pageable);

    /**
     * Removes a privilege from a role.
     *
     * @param id
     *            role id
     * @param privilege
     *            privilege id to remove
     * @return {@code true} if it managed to remove the privilege, {@code false} otherwise
     */
    public Boolean removePrivilege(final Long id, final Long privilege);

    /**
     * Updates the role for the received id with the received data.
     *
     * @param role
     *            new data for the role
     * @return the updated role
     */
    public Role update(final Role role);

}
