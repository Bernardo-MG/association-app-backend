
package com.bernardomg.association.paidmonth.service;

import java.util.Optional;

import com.bernardomg.association.paidmonth.model.PaidMonth;

/**
 * Paid month service. Supports all the CRUD operations.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 *
 */
public interface PaidMonthService {

    public PaidMonth create(final PaidMonth month);

    public Boolean delete(final Long id);

    public Iterable<? extends PaidMonth> getAll(final PaidMonth example);

    public Optional<? extends PaidMonth> getOne(final Long id);

    public PaidMonth update(final Long id, final PaidMonth month);

}
