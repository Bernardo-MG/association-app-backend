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

package com.bernardomg.association.test.fee.calendar.integration.service;

import java.util.Iterator;

import org.apache.commons.collections4.IterableUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import com.bernardomg.association.fee.calendar.model.FeeCalendar;
import com.bernardomg.association.fee.calendar.model.FeeMonth;
import com.bernardomg.association.fee.calendar.service.FeeCalendarService;
import com.bernardomg.association.test.config.annotation.IntegrationTest;

@IntegrationTest
@DisplayName("Fee calendar service - get all - inactive member")
@Sql({ "/db/queries/member/inactive.sql", "/db/queries/fee/full_year.sql" })
public class ITFeeCalendarServiceGetAllInactiveMember {

    @Autowired
    private FeeCalendarService service;

    public ITFeeCalendarServiceGetAllInactiveMember() {
        super();
    }

    @Test
    @DisplayName("Returns all the entities")
    public void testGetAll_Count() {
        final Iterable<? extends FeeCalendar> result;
        final Sort                            sort;

        sort = Sort.unsorted();

        result = service.getAll(2020, false, sort);

        Assertions.assertEquals(1, IterableUtils.size(result));
        Assertions.assertEquals(12, IterableUtils.size(result.iterator()
            .next()
            .getMonths()));
    }

    @Test
    @DisplayName("Returns all data")
    public void testGetAll_Data() {
        final Iterator<? extends FeeCalendar> data;
        FeeCalendar                           result;
        Iterator<FeeMonth>                    months;
        FeeMonth                              month;
        final Sort                            sort;

        sort = Sort.unsorted();

        data = service.getAll(2020, false, sort)
            .iterator();

        result = data.next();
        Assertions.assertEquals(1, result.getMemberId());
        Assertions.assertEquals("Member 1", result.getName());
        Assertions.assertEquals("Surname 1", result.getSurname());
        Assertions.assertEquals(2020, result.getYear());
        Assertions.assertEquals(false, result.getActive());

        months = result.getMonths()
            .iterator();

        month = months.next();
        Assertions.assertEquals(1, month.getMonth());
        Assertions.assertEquals(true, month.getPaid());

        month = months.next();
        Assertions.assertEquals(2, month.getMonth());
        Assertions.assertEquals(true, month.getPaid());

        month = months.next();
        Assertions.assertEquals(3, month.getMonth());
        Assertions.assertEquals(true, month.getPaid());

        month = months.next();
        Assertions.assertEquals(4, month.getMonth());
        Assertions.assertEquals(true, month.getPaid());

        month = months.next();
        Assertions.assertEquals(5, month.getMonth());
        Assertions.assertEquals(true, month.getPaid());

        month = months.next();
        Assertions.assertEquals(6, month.getMonth());
        Assertions.assertEquals(true, month.getPaid());

        month = months.next();
        Assertions.assertEquals(7, month.getMonth());
        Assertions.assertEquals(true, month.getPaid());

        month = months.next();
        Assertions.assertEquals(8, month.getMonth());
        Assertions.assertEquals(true, month.getPaid());

        month = months.next();
        Assertions.assertEquals(9, month.getMonth());
        Assertions.assertEquals(true, month.getPaid());

        month = months.next();
        Assertions.assertEquals(10, month.getMonth());
        Assertions.assertEquals(true, month.getPaid());

        month = months.next();
        Assertions.assertEquals(11, month.getMonth());
        Assertions.assertEquals(true, month.getPaid());

        month = months.next();
        Assertions.assertEquals(12, month.getMonth());
        Assertions.assertEquals(true, month.getPaid());
    }

}
