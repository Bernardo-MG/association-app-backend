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

package com.bernardomg.association.status.feeyear.model;

import java.util.Collection;

import lombok.Data;
import lombok.NonNull;

@Data
public final class ImmutableFeeYear implements FeeYear {

    @NonNull
    private final Boolean              active;

    @NonNull
    private final Long                 memberId;

    @NonNull
    private final Collection<FeeMonth> months;

    @NonNull
    private final String               name;

    @NonNull
    private final String               surname;

    @NonNull
    private final Integer              year;

    public ImmutableFeeYear(@NonNull final Long id, @NonNull final String nm, @NonNull final String surnm,
            @NonNull final Boolean actv, @NonNull final Collection<FeeMonth> mnths, @NonNull final Integer yr) {
        super();

        memberId = id;
        name = nm;
        surname = surnm;
        active = actv;
        months = mnths;
        year = yr;
    }

}