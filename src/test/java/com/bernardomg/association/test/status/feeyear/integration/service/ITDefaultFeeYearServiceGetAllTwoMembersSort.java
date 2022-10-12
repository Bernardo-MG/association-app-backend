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

package com.bernardomg.association.test.status.feeyear.integration.service;

import java.util.Iterator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.jdbc.Sql;

import com.bernardomg.association.status.feeyear.model.FeeMonth;
import com.bernardomg.association.status.feeyear.model.FeeYear;
import com.bernardomg.association.status.feeyear.service.DefaultFeeYearService;
import com.bernardomg.association.test.config.annotation.IntegrationTest;

@IntegrationTest
@DisplayName("Default fee year service - get all - two members - sort")
@Sql({ "/db/queries/member/single.sql", "/db/queries/member/alternative.sql", "/db/queries/fee/full_year.sql",
        "/db/queries/fee/full_year_alternative.sql" })
public class ITDefaultFeeYearServiceGetAllTwoMembersSort {

    @Autowired
    private DefaultFeeYearService service;

    public ITDefaultFeeYearServiceGetAllTwoMembersSort() {
        super();
    }

    @Test
    @DisplayName("Returns all data in ascending order by name")
    public void testGetAll_Name_Asc() {
        final Iterator<? extends FeeYear> data;
        FeeYear                           result;
        Iterator<FeeMonth>                months;
        FeeMonth                          month;
        final Sort                        sort;

        sort = Sort.by(Direction.ASC, "name");

        data = service.getAll(2020, sort)
            .iterator();

        // First member
        result = data.next();
        Assertions.assertEquals(1, result.getMemberId());
        Assertions.assertEquals("Member 1", result.getName());
        Assertions.assertEquals("Surname 1", result.getSurname());
        Assertions.assertEquals(2020, result.getYear());
        Assertions.assertEquals(true, result.getActive());

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

        // Second member
        result = data.next();
        Assertions.assertEquals(2, result.getMemberId());
        Assertions.assertEquals("Member 2", result.getName());
        Assertions.assertEquals("Surname 2", result.getSurname());
        Assertions.assertEquals(2020, result.getYear());
        Assertions.assertEquals(true, result.getActive());

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

    @Test
    @DisplayName("Returns all data in descending order by name")
    public void testGetAll_Name_Desc() {
        final Iterator<? extends FeeYear> data;
        FeeYear                           result;
        Iterator<FeeMonth>                months;
        FeeMonth                          month;
        final Sort                        sort;

        sort = Sort.by(Direction.DESC, "name");

        data = service.getAll(2020, sort)
            .iterator();

        // Second member
        result = data.next();
        Assertions.assertEquals(2, result.getMemberId());
        Assertions.assertEquals("Member 2", result.getName());
        Assertions.assertEquals("Surname 2", result.getSurname());
        Assertions.assertEquals(2020, result.getYear());
        Assertions.assertEquals(true, result.getActive());

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

        // First member
        result = data.next();
        Assertions.assertEquals(1, result.getMemberId());
        Assertions.assertEquals("Member 1", result.getName());
        Assertions.assertEquals("Surname 1", result.getSurname());
        Assertions.assertEquals(2020, result.getYear());
        Assertions.assertEquals(true, result.getActive());

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

    @Test
    @DisplayName("Returns all data in ascending order by surname")
    public void testGetAll_Surname_Asc() {
        final Iterator<? extends FeeYear> data;
        FeeYear                           result;
        Iterator<FeeMonth>                months;
        FeeMonth                          month;
        final Sort                        sort;

        sort = Sort.by(Direction.ASC, "surname");

        data = service.getAll(2020, sort)
            .iterator();

        // First member
        result = data.next();
        Assertions.assertEquals(1, result.getMemberId());
        Assertions.assertEquals("Member 1", result.getName());
        Assertions.assertEquals("Surname 1", result.getSurname());
        Assertions.assertEquals(2020, result.getYear());
        Assertions.assertEquals(true, result.getActive());

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

        // Second member
        result = data.next();
        Assertions.assertEquals(2, result.getMemberId());
        Assertions.assertEquals("Member 2", result.getName());
        Assertions.assertEquals("Surname 2", result.getSurname());
        Assertions.assertEquals(2020, result.getYear());
        Assertions.assertEquals(true, result.getActive());

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

    @Test
    @DisplayName("Returns all data in descending order by surname")
    public void testGetAll_Surname_Desc() {
        final Iterator<? extends FeeYear> data;
        FeeYear                           result;
        Iterator<FeeMonth>                months;
        FeeMonth                          month;
        final Sort                        sort;

        sort = Sort.by(Direction.DESC, "surname");

        data = service.getAll(2020, sort)
            .iterator();

        // Second member
        result = data.next();
        Assertions.assertEquals(2, result.getMemberId());
        Assertions.assertEquals("Member 2", result.getName());
        Assertions.assertEquals("Surname 2", result.getSurname());
        Assertions.assertEquals(2020, result.getYear());
        Assertions.assertEquals(true, result.getActive());

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

        // First member
        result = data.next();
        Assertions.assertEquals(1, result.getMemberId());
        Assertions.assertEquals("Member 1", result.getName());
        Assertions.assertEquals("Surname 1", result.getSurname());
        Assertions.assertEquals(2020, result.getYear());
        Assertions.assertEquals(true, result.getActive());

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