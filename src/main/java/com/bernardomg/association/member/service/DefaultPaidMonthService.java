
package com.bernardomg.association.member.service;

import java.util.stream.Collectors;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.bernardomg.association.member.model.DtoMemberMonth;
import com.bernardomg.association.member.model.MemberMonth;
import com.bernardomg.association.member.model.PersistentPaidMonth;
import com.bernardomg.association.member.repository.PaidMonthRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public final class DefaultPaidMonthService implements PaidMonthService {

    private final PaidMonthRepository repository;

    @Override
    public final MemberMonth create(final Long member, final MemberMonth month) {
        final PersistentPaidMonth entity;
        final PersistentPaidMonth created;

        entity = toPersistentPaidMonth(month);
        entity.setMember(member);

        created = repository.save(entity);
        return toPaidMonth(created);
    }

    @Override
    public final Boolean delete(final Long id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public final Iterable<? extends MemberMonth> getAllForMember(final Long member) {
        final Example<PersistentPaidMonth> example;
        final PersistentPaidMonth          entity;

        entity = new PersistentPaidMonth();
        entity.setMember(member);

        example = Example.of(entity);

        // TODO: Sort by date
        return repository.findAll(example)
            .stream()
            .map(this::toPaidMonth)
            .collect(Collectors.toList());
    }

    private final MemberMonth toPaidMonth(final PersistentPaidMonth entity) {
        final DtoMemberMonth data;

        data = new DtoMemberMonth();
        data.setId(entity.getId());
        data.setMember(entity.getMember());
        data.setMonth(entity.getMonth());
        data.setYear(entity.getYear());

        return data;
    }

    private final PersistentPaidMonth toPersistentPaidMonth(final MemberMonth data) {
        final PersistentPaidMonth entity;

        entity = new PersistentPaidMonth();
        entity.setId(data.getId());
        entity.setMember(data.getMember());
        entity.setMonth(data.getMonth());
        entity.setYear(data.getYear());

        return entity;
    }

}
