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

package com.bernardomg.association.test.member.integration.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import com.bernardomg.association.member.model.DtoMemberPeriod;
import com.bernardomg.association.member.model.MemberPeriod;
import com.bernardomg.association.member.model.PersistentMemberPeriod;
import com.bernardomg.association.member.repository.MemberPeriodRepository;
import com.bernardomg.association.member.service.DefaultMemberPeriodService;
import com.bernardomg.association.test.config.annotation.IntegrationTest;

@IntegrationTest
@DisplayName("Default member period service - update")
@Sql({ "/db/queries/member_period/single.sql" })
public class ITDefaultMemberPeriodServiceUpdate {

    @Autowired
    private MemberPeriodRepository     repository;

    @Autowired
    private DefaultMemberPeriodService service;

    public ITDefaultMemberPeriodServiceUpdate() {
        super();

        // TODO: Check invalid ids
    }

    @Test
    @DisplayName("Returns the previous data")
    public void testCreate_ReturnedData() {
        final MemberPeriod    result;
        final DtoMemberPeriod period;

        period = new DtoMemberPeriod();
        period.setStartMonth(20);
        period.setStartYear(30);
        period.setEndMonth(40);
        period.setEndYear(50);

        result = service.update(1L, getId(), period);

        Assertions.assertNotNull(result.getId());
        Assertions.assertEquals(1, result.getMember());
        Assertions.assertEquals(20, result.getStartMonth());
        Assertions.assertEquals(30, result.getStartYear());
        Assertions.assertEquals(40, result.getEndMonth());
        Assertions.assertEquals(50, result.getEndYear());
    }

    @Test
    @DisplayName("Adds no entity when updating")
    public void testUpdate_AddsNoEntity() {
        final DtoMemberPeriod period;

        period = new DtoMemberPeriod();
        period.setStartMonth(20);
        period.setStartYear(30);
        period.setEndMonth(40);
        period.setEndYear(50);

        service.update(1L, getId(), period);

        Assertions.assertEquals(1L, repository.count());
    }

    @Test
    @DisplayName("Updates persisted data")
    public void testUpdate_PersistedData() {
        final DtoMemberPeriod        period;
        final PersistentMemberPeriod entity;

        period = new DtoMemberPeriod();
        period.setStartMonth(20);
        period.setStartYear(30);
        period.setEndMonth(40);
        period.setEndYear(50);

        service.update(1L, getId(), period);
        entity = repository.findAll()
            .iterator()
            .next();

        Assertions.assertNotNull(entity.getId());
        Assertions.assertEquals(1, entity.getMember());
        Assertions.assertEquals(20, entity.getStartMonth());
        Assertions.assertEquals(30, entity.getStartYear());
        Assertions.assertEquals(40, entity.getEndMonth());
        Assertions.assertEquals(50, entity.getEndYear());
    }

    private final Long getId() {
        return repository.findAll()
            .iterator()
            .next()
            .getId();
    }

}