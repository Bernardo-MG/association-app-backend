/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2022 the original author or authors.
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.bernardomg.association.test.transaction.integration.service;

import java.util.GregorianCalendar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import com.bernardomg.association.test.config.annotation.IntegrationTest;
import com.bernardomg.association.transaction.model.DtoTransaction;
import com.bernardomg.association.transaction.model.PersistentTransaction;
import com.bernardomg.association.transaction.model.Transaction;
import com.bernardomg.association.transaction.repository.TransactionRepository;
import com.bernardomg.association.transaction.service.TransactionService;

@IntegrationTest
@DisplayName("Transaction service - update")
@Sql({ "/db/queries/transaction/single.sql" })
public class ITTransactionServiceUpdate {

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private TransactionService    service;

    public ITTransactionServiceUpdate() {
        super();
    }

    @Test
    @DisplayName("Adds no entity when updating")
    public void testUpdate_AddsNoEntity() {
        final DtoTransaction transaction;

        transaction = new DtoTransaction();
        transaction.setDescription("Transaction 123");
        transaction.setAmount(1f);
        transaction.setDate(new GregorianCalendar(2020, 1, 1));

        service.update(1L, transaction);

        Assertions.assertEquals(1L, repository.count());
    }

    @Test
    @DisplayName("Updates persisted data with decimal values")
    public void testUpdate_Decimal_PersistedData() {
        final DtoTransaction        transaction;
        final PersistentTransaction entity;

        transaction = new DtoTransaction();
        transaction.setDescription("Transaction 123");
        transaction.setAmount(1.2f);
        transaction.setDate(new GregorianCalendar(2020, 1, 1));

        service.update(1L, transaction);
        entity = repository.findAll()
            .iterator()
            .next();

        Assertions.assertNotNull(entity.getId());
        Assertions.assertEquals("Transaction 123", entity.getDescription());
        Assertions.assertEquals(new GregorianCalendar(2020, 1, 1).toInstant(), entity.getDate()
            .toInstant());
        Assertions.assertEquals(1.2f, entity.getAmount());
    }

    @Test
    @DisplayName("Returns the updated data with decimal values")
    public void testUpdate_Decimal_ReturnedData() {
        final Transaction    result;
        final DtoTransaction transaction;

        transaction = new DtoTransaction();
        transaction.setDescription("Transaction");
        transaction.setAmount(1.2f);
        transaction.setDate(new GregorianCalendar(2020, 1, 1));

        result = service.update(1L, transaction);

        Assertions.assertNotNull(result.getId());
        Assertions.assertEquals("Transaction", result.getDescription());
        Assertions.assertEquals(new GregorianCalendar(2020, 1, 1).toInstant(), result.getDate()
            .toInstant());
        Assertions.assertEquals(1.2f, result.getAmount());
    }

    @Test
    @DisplayName("When updating a not existing entity a new one is added")
    public void testUpdate_NotExisting_AddsEntity() {
        final DtoTransaction transaction;

        transaction = new DtoTransaction();
        transaction.setDescription("Transaction 123");
        transaction.setAmount(1f);
        transaction.setDate(new GregorianCalendar(2020, 1, 1));

        service.update(10L, transaction);

        Assertions.assertEquals(2L, repository.count());
    }

    @Test
    @DisplayName("Updates persisted data")
    public void testUpdate_PersistedData() {
        final DtoTransaction        transaction;
        final PersistentTransaction entity;

        transaction = new DtoTransaction();
        transaction.setDescription("Transaction 123");
        transaction.setAmount(1f);
        transaction.setDate(new GregorianCalendar(2020, 1, 1));

        service.update(1L, transaction);
        entity = repository.findAll()
            .iterator()
            .next();

        Assertions.assertNotNull(entity.getId());
        Assertions.assertEquals("Transaction 123", entity.getDescription());
        Assertions.assertEquals(new GregorianCalendar(2020, 1, 1).toInstant(), entity.getDate()
            .toInstant());
        Assertions.assertEquals(1f, entity.getAmount());
    }

    @Test
    @DisplayName("Returns the updated data")
    public void testUpdate_ReturnedData() {
        final Transaction    result;
        final DtoTransaction transaction;

        transaction = new DtoTransaction();
        transaction.setDescription("Transaction");
        transaction.setAmount(1f);
        transaction.setDate(new GregorianCalendar(2020, 1, 1));

        result = service.update(1L, transaction);

        Assertions.assertNotNull(result.getId());
        Assertions.assertEquals("Transaction", result.getDescription());
        Assertions.assertEquals(new GregorianCalendar(2020, 1, 1).toInstant(), result.getDate()
            .toInstant());
        Assertions.assertEquals(1f, result.getAmount());
    }

}
