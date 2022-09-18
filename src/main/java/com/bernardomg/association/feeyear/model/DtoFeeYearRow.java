
package com.bernardomg.association.feeyear.model;

import java.util.Calendar;

import lombok.Data;

@Data
public final class DtoFeeYearRow implements FeeYearRow {

    private Boolean  active;

    private Long     id;

    private String   member;

    private Long     memberId;

    private Boolean  paid;

    private Calendar payDate;

}