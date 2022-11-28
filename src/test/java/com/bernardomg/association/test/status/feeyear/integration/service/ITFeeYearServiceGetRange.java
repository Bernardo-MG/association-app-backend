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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import com.bernardomg.association.status.feeyear.model.FeeYearRange;
import com.bernardomg.association.status.feeyear.service.FeeYearService;
import com.bernardomg.association.test.config.annotation.IntegrationTest;

@IntegrationTest
@DisplayName("Fee year service - get all")
public class ITFeeYearServiceGetRange {

    @Autowired
    private FeeYearService service;

    public ITFeeYearServiceGetRange() {
        super();
    }

    @Test
    @DisplayName("With a full year the year range is returned")
    @Sql({ "/db/queries/member/single.sql", "/db/queries/fee/full_year.sql" })
    public void testGetRange_FullYear() {
        final FeeYearRange result;

        result = service.getRange(false);

        Assertions.assertEquals(2020, result.getStart());
        Assertions.assertEquals(2020, result.getEnd());
    }

    @Test
    @DisplayName("With a full year and two members the year range is returned")
    @Sql({ "/db/queries/member/single.sql", "/db/queries/member/alternative.sql", "/db/queries/fee/full_year.sql",
            "/db/queries/fee/full_year_alternative.sql" })
    public void testGetRange_FullYear_TwoMembers() {
        final FeeYearRange result;

        result = service.getRange(false);

        Assertions.assertEquals(2020, result.getStart());
        Assertions.assertEquals(2020, result.getEnd());
    }

    @Test
    @DisplayName("Returns the range for an inactive member")
    @Sql({ "/db/queries/member/inactive.sql", "/db/queries/fee/full_year.sql" })
    public void testGetRange_Inactive() {
        final FeeYearRange result;

        result = service.getRange(false);

        Assertions.assertEquals(2020, result.getStart());
        Assertions.assertEquals(2020, result.getEnd());
    }

    @Test
    @DisplayName("With no data the range is empty")
    public void testGetRange_NoData() {
        final FeeYearRange result;

        result = service.getRange(false);

        Assertions.assertEquals(0, result.getStart());
        Assertions.assertEquals(0, result.getEnd());
    }

    @Test
    @DisplayName("With a single fee the year range is returned")
    @Sql({ "/db/queries/member/single.sql", "/db/queries/fee/single.sql" })
    public void testGetRange_Single() {
        final FeeYearRange result;

        result = service.getRange(false);

        Assertions.assertEquals(2020, result.getStart());
        Assertions.assertEquals(2020, result.getEnd());
    }

    @Test
    @DisplayName("With two years connected the year range is returned")
    @Sql({ "/db/queries/member/single.sql", "/db/queries/fee/two_years_connected.sql" })
    public void testGetRange_TwoConnectedYears() {
        final FeeYearRange result;

        result = service.getRange(false);

        Assertions.assertEquals(2019, result.getStart());
        Assertions.assertEquals(2020, result.getEnd());
    }

    @Test
    @DisplayName("With two years with a gap the year range is returned")
    @Sql({ "/db/queries/member/single.sql", "/db/queries/fee/two_years_gap.sql" })
    public void testGetRange_TwoYearsWithGap() {
        final FeeYearRange result;

        result = service.getRange(false);

        Assertions.assertEquals(2018, result.getStart());
        Assertions.assertEquals(2020, result.getEnd());
    }

}