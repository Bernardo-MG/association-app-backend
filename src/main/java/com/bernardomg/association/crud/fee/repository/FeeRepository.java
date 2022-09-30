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

package com.bernardomg.association.crud.fee.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bernardomg.association.crud.fee.model.Fee;
import com.bernardomg.association.crud.fee.model.PersistentFee;

public interface FeeRepository extends JpaRepository<PersistentFee, Long> {

    @Query("SELECT f.id AS id, TRIM(CONCAT(m.name, ' ',  m.surname)) AS member, m.id AS memberId, f.payDate AS payDate, f.paid AS paid FROM Fee f JOIN Member m ON f.memberId = m.id")
    public Page<Fee> findAllWithMember(final Example<PersistentFee> example, final Pageable pageable);

    @Query("SELECT f.id AS id, TRIM(CONCAT(m.name, ' ',  m.surname)) AS member, m.id AS memberId, f.payDate AS payDate, f.paid AS paid FROM Fee f JOIN Member m ON f.memberId = m.id WHERE YEAR(f.payDate) = :year")
    public List<Fee> findAllWithMemberForYear(@Param("year") final Integer year, final Sort sort);

    @Query("SELECT f.id AS id, TRIM(CONCAT(m.name, ' ',  m.surname)) AS member, m.id AS memberId, f.payDate AS payDate, f.paid AS paid FROM Fee f JOIN Member m ON f.memberId = m.id WHERE f.id = :id")
    public Optional<Fee> findByIdWithMember(@Param("id") final Long id);

}