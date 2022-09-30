
package com.bernardomg.association.status.memberstats.model;

import lombok.Data;

@Data
public final class DtoMemberStats implements MemberStats {

    private Integer active;

    private Integer inactive;

    private Integer total;

}