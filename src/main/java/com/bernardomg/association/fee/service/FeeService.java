
package com.bernardomg.association.fee.service;

import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.bernardomg.association.fee.model.FeeForm;
import com.bernardomg.association.fee.model.FeeRequest;
import com.bernardomg.association.fee.model.MemberFee;

/**
 * Fee service. Supports all the CRUD operations.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 *
 */
public interface FeeService {

    /**
     * Persists the received fee.
     *
     * @param fee
     *            fee to persist
     * @return the persisted fee
     */
    public MemberFee create(final FeeForm fee);

    /**
     * Deletes the fee with the received id.
     *
     * @param id
     *            id of the fee to delete
     * @return {@code true} if it managed to delete, {@code false} otherwise
     */
    public Boolean delete(final Long id);

    /**
     * Returns all the fees matching the sample. If the sample fields are empty, then all the fees are returned.
     *
     * @param request
     *            sample for filtering
     * @param pageable
     *            pagination to apply
     * @return all the fees matching the sample
     */
    public Iterable<? extends MemberFee> getAll(final FeeRequest request, final Pageable pageable);

    /**
     * Returns the fee for the received id, if it exists. Otherwise an empty {@code Optional} is returned.
     *
     * @param id
     *            id of the fee to acquire
     * @return an {@code Optional} with the fee, if it exists, of an empty {@code Optional} otherwise
     */
    public Optional<? extends MemberFee> getOne(final Long id);

    /**
     * Updates the fee for the received id with the received data.
     *
     * @param id
     *            id of the fee to update
     * @param fee
     *            new data for the fee
     * @return the updated fee
     */
    public MemberFee update(final Long id, final FeeForm fee);

}
