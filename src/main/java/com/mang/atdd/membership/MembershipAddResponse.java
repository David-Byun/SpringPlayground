package com.mang.atdd.membership;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MembershipAddResponse {

    private final Long id;
    private final MembershipType membershipType;
}
