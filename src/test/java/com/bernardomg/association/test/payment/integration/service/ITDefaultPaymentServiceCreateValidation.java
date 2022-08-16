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

package com.bernardomg.association.test.payment.integration.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;

import com.bernardomg.association.payment.model.DtoPayment;
import com.bernardomg.association.payment.model.PaymentType;
import com.bernardomg.association.payment.service.DefaultPaymentService;
import com.bernardomg.association.test.config.annotation.IntegrationTest;
import com.bernardomg.validation.exception.ValidationException;

@IntegrationTest
@DisplayName("Default payment service - create validation")
public class ITDefaultPaymentServiceCreateValidation {

    @Autowired
    private DefaultPaymentService service;

    public ITDefaultPaymentServiceCreateValidation() {
        super();
    }

    @Test
    @DisplayName("Throws an exception when the month is above limits")
    public void testCreate_MonthAboveLimit() {
        final DtoPayment payment;
        final Executable executable;
        final Exception  exception;

        payment = new DtoPayment();
        payment.setDescription("Payment");
        payment.setType(PaymentType.INCOME);
        payment.setQuantity(1l);
        payment.setDay(2);
        payment.setMonth(13);
        payment.setYear(4);

        executable = () -> service.create(payment);

        exception = Assertions.assertThrows(ValidationException.class, executable);

        Assertions.assertEquals("error.payment.invalidMonth", exception.getMessage());
    }

    @Test
    @DisplayName("Throws an exception when the month is below limits")
    public void testCreate_MonthBelowLimit() {
        final DtoPayment payment;
        final Executable executable;
        final Exception  exception;

        payment = new DtoPayment();
        payment.setDescription("Payment");
        payment.setType(PaymentType.INCOME);
        payment.setQuantity(1l);
        payment.setDay(2);
        payment.setMonth(0);
        payment.setYear(4);

        executable = () -> service.create(payment);

        exception = Assertions.assertThrows(ValidationException.class, executable);

        Assertions.assertEquals("error.payment.invalidMonth", exception.getMessage());
    }

}