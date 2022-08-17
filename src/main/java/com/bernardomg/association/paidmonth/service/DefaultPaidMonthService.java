
package com.bernardomg.association.paidmonth.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.bernardomg.association.paidmonth.model.DtoPaidMonth;
import com.bernardomg.association.paidmonth.model.PaidMonth;
import com.bernardomg.association.paidmonth.model.PersistentPaidMonth;
import com.bernardomg.association.paidmonth.repository.PaidMonthRepository;
import com.bernardomg.association.paidmonth.validation.PaidMonthValidator;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public final class DefaultPaidMonthService implements PaidMonthService {

    private final PaidMonthRepository repository;

    private final PaidMonthValidator  validator;

    @Override
    public final PaidMonth create(final PaidMonth month) {
        final PersistentPaidMonth entity;
        final PersistentPaidMonth created;

        validator.validate(month);

        entity = toEntity(month);

        created = repository.save(entity);
        return toDto(created);
    }

    @Override
    public final Boolean delete(final Long id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public final Iterable<? extends PaidMonth> getAll(final PaidMonth sample) {
        final PersistentPaidMonth entity;
        final Sort                sort;

        entity = toEntity(sample);

        sort = Sort.by(Direction.ASC, "month", "year");

        return repository.findAll(Example.of(entity), sort)
            .stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public final Optional<? extends PaidMonth> getOne(final Long id) {
        final Optional<PersistentPaidMonth> found;
        final Optional<? extends PaidMonth> result;
        final PaidMonth                     member;

        found = repository.findById(id);

        if (found.isPresent()) {
            member = toDto(found.get());
            result = Optional.of(member);
        } else {
            result = Optional.empty();
        }

        return result;
    }

    @Override
    public final PaidMonth update(final Long member, final PaidMonth month) {
        final PersistentPaidMonth entity;
        final PersistentPaidMonth created;

        validator.validate(month);

        entity = toEntity(month);
        entity.setMember(member);

        created = repository.save(entity);
        return toDto(created);
    }

    private final PaidMonth toDto(final PersistentPaidMonth entity) {
        final DtoPaidMonth data;

        data = new DtoPaidMonth();
        data.setId(entity.getId());
        data.setMember(entity.getMember());
        data.setMonth(entity.getMonth());
        data.setYear(entity.getYear());
        data.setPaid(entity.getPaid());

        return data;
    }

    private final PersistentPaidMonth toEntity(final PaidMonth data) {
        final PersistentPaidMonth entity;

        entity = new PersistentPaidMonth();
        entity.setId(data.getId());
        entity.setMember(data.getMember());
        entity.setMonth(data.getMonth());
        entity.setYear(data.getYear());
        entity.setPaid(data.getPaid());

        return entity;
    }

}
