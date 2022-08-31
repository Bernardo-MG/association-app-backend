
package com.bernardomg.association.feeyear.service;

import com.bernardomg.association.feeyear.model.FeeYear;

/**
 * Fee service. Supports all the CRUD operations.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 *
 */
public interface FeeYearService {

    public Iterable<? extends FeeYear> getAll(final Integer year);

}
